package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.InvoiceBatchDao;
import nl.codebasesoftware.produx.dao.InvoiceDao;
import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.domain.assembler.InvoiceAssembler;
import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceBatchEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;
import nl.codebasesoftware.produx.domain.support.InvoiceProcessingAttemptStatus;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.net.mail.InvoiceMailer;
import nl.codebasesoftware.produx.service.CourseRequestService;
import nl.codebasesoftware.produx.service.InvoiceService;
import nl.codebasesoftware.produx.service.LinkClickService;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import nl.codebasesoftware.produx.properties.Properties;
import nl.codebasesoftware.produx.util.collection.EntityCollectionConverter;
import nl.codebasesoftware.produx.util.pdf.PdfGenerator;
import org.apache.fop.apps.FOPException;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.util.*;

/**
 * User: rvanloen
 * Date: 27-10-13
 * Time: 12:45
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {


    private CourseRequestService courseRequestService;
    private VelocityEngine velocityEngine;
    private InvoiceDao invoiceDao;
    private Properties properties;
    private CompanyDao companyDao;
    private PdfGenerator generator;
    private InvoiceMailer invoiceMailer;
    private InvoiceAssembler invoiceAssembler;
    private LinkClickService clickService;
    private InvoiceBatchDao invoiceBatchDao;


    @Autowired
    public InvoiceServiceImpl(CourseRequestService courseRequestService, VelocityEngine velocityEngine,
                              InvoiceDao invoiceDao, Properties properties, CompanyDao companyDao,
                              PdfGenerator generator, InvoiceMailer invoiceMailer,
                              InvoiceAssembler invoiceAssembler,
                              LinkClickService clickService,
                              InvoiceBatchDao invoiceBatchDao) {
        this.courseRequestService = courseRequestService;
        this.velocityEngine = velocityEngine;
        this.invoiceDao = invoiceDao;
        this.properties = properties;
        this.companyDao = companyDao;
        this.generator = generator;
        this.invoiceMailer = invoiceMailer;
        this.invoiceAssembler = invoiceAssembler;
        this.clickService = clickService;
        this.invoiceBatchDao = invoiceBatchDao;
    }


    public void runLastMonthInvoiceBatch() throws ProduxServiceException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        runInvoiceBatch(month, year);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public InvoiceBatchEntityDTO runInvoiceBatch(int month, int year) throws ProduxServiceException{

        List<Company> all = companyDao.findAll();

        List<InvoiceBatch> batchedForMonthAndYear = invoiceBatchDao.findByMonthAndYear(month, year);

        if(batchedForMonthAndYear.size() > 0){
            throw new ProduxServiceException(String.format("A batch for %d/%d has already been run! Be careful not to run the same batch again.", month, year));
        }

        InvoiceBatch batch = new InvoiceBatch();
        batch.setJobStarted(Calendar.getInstance());
        batch.setMonth(month);
        batch.setYear(year);

        for (Company company : all) {
            InvoiceProcessingAttempt attempt = generateInvoiceOrDoNothing(company.getId(), new MonthAndYear(month, year));
            if(attempt != null){
                batch.addInvoiceProcessingAttempt(attempt);
            }
        }

        batch.setJobCompleted(Calendar.getInstance());
        invoiceBatchDao.save(batch);
        return batch.toDTO();
    }


    private InvoiceProcessingAttempt generateInvoiceOrDoNothing(long companyId, MonthAndYear monthAndYear){

        Company company = companyDao.find(companyId);
        List<CourseRequestEntityDTO> requests = courseRequestService.findForMonth(company.getId(), monthAndYear);
        List<ClickEntityDTO> clicks = clickService.findForMonth(companyId, monthAndYear);

        if(requests.size() > 0 || clicks.size() > 0) {

            InvoiceProcessingAttempt attempt = new InvoiceProcessingAttempt();
            try {
                Invoice invoice = createInDb(requests, clicks, company,  monthAndYear);
                attempt.setInvoice(invoice);
                File pdf = createPdf(monthAndYear, invoice.toDTO());
                attempt.setTimeSent(Calendar.getInstance());
                invoiceMailer.sendInvoiceEmail(pdf, invoice.toDTO(), LocaleContextHolder.getLocale());
                attempt.setStatus(InvoiceProcessingAttemptStatus.SUCCESS);
            } catch(Exception e){
                handleException(attempt, e);
            }

            return attempt;
        }

        return null;
    }

    private void handleException(InvoiceProcessingAttempt attempt, Exception e){
        attempt.setStatus(InvoiceProcessingAttemptStatus.FAIL);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        attempt.setExceptionStackTrace(sw.toString());
        e.printStackTrace();
    }


    private File createPdf(MonthAndYear monthAndYear, InvoiceEntityDTO invoice) throws TransformerException, IOException, FOPException {

        Map<String, Object> model = new HashMap<>();
        model.put("invoice", invoice);
        model.put("month", DateFormatSymbols.getInstance().getMonths()[monthAndYear.getMonth() - 1]);
        model.put("logoUrl", properties.getProperty("invoices.logoUrl"));

        String invoicesPath = properties.getProperty("invoices.path");
        Path pdfFileDir = Paths.get(invoicesPath);
        Path pdfFilePath = pdfFileDir.resolve(invoice.getFileName("pdf"));
        File pdfFile = pdfFilePath.toFile();

        File xslTempFile = createTempXslFile(invoice.getFileName("xsl"), model, "/velocity/pdf/invoice.vm");

        return generator.generate(xslTempFile, pdfFile);
    }

    private Invoice createInDb(List<CourseRequestEntityDTO> requests, List<ClickEntityDTO> clicks, Company company, MonthAndYear monthAndYear){
        Invoice invoice = invoiceAssembler.assemble(company, requests, clicks, monthAndYear);
        invoiceDao.save(invoice);
        return invoice;
    }


    private File createTempXslFile(String fileName, Map<String, Object> model, String templateName) {
        String invoiceXslFo = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, model);
        String tempXslPathString = properties.getProperty("invoices.tempxsl.path");
        Path tempXslDirPath = Paths.get(tempXslPathString);
        Path tempXslFilePath = tempXslDirPath.resolve(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(tempXslFilePath, Charset.defaultCharset())) {
            writer.append(invoiceXslFo);
            writer.flush();
        } catch (IOException exception) {
            System.out.println("Error writing to file");
        }

        return tempXslFilePath.toFile();
    }

    @Override
    @Transactional(readOnly = true)
    public File fromDisk(long id) throws ProduxServiceException {
        String dir = properties.getProperty("invoices.path");
        InvoiceEntityDTO invoice = invoiceDao.find(id).toDTO();
        String fileName = invoice.getFileName("pdf");
        Path dirPath = Paths.get(dir);
        existsAndReadable(dirPath);
        Path invoiceFilePath = dirPath.resolve(fileName);
        existsAndReadable(invoiceFilePath);
        return invoiceFilePath.toFile();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceEntityDTO> findForCompany(long companyId, int year){
        List<Invoice> invoices = invoiceDao.find(companyId, year);
        return new EntityCollectionConverter<Invoice, InvoiceEntityDTO>().convert(invoices);
    }



    public void existsAndReadable(Path path) throws ProduxServiceException {
        if (!Files.exists(path) || !Files.isReadable(path)) {
            throw new ProduxServiceException(String.format("Directory %s not found or not readable", path));
        }
    }

}
