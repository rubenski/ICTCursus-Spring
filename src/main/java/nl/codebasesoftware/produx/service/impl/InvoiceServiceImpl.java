package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.InvoiceDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Invoice;
import nl.codebasesoftware.produx.domain.assembler.InvoiceAssembler;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.net.mail.InvoiceMailer;
import nl.codebasesoftware.produx.service.CourseRequestService;
import nl.codebasesoftware.produx.service.InvoiceService;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.util.collection.EntityCollectionConverter;
import nl.codebasesoftware.produx.util.pdf.PdfGenerator;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
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


    @Autowired
    public InvoiceServiceImpl(CourseRequestService courseRequestService, VelocityEngine velocityEngine,
                              InvoiceDao invoiceDao, Properties properties, CompanyDao companyDao,
                              PdfGenerator generator, InvoiceMailer invoiceMailer,
                              InvoiceAssembler invoiceAssembler) {
        this.courseRequestService = courseRequestService;
        this.velocityEngine = velocityEngine;
        this.invoiceDao = invoiceDao;
        this.properties = properties;
        this.companyDao = companyDao;
        this.generator = generator;
        this.invoiceMailer = invoiceMailer;
        this.invoiceAssembler = invoiceAssembler;
    }

    @Override
    @Transactional(readOnly = false)
    public void runLastMonthInvoiceBatch() {
        List<Company> all = companyDao.findAll();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        for (Company company : all) {
            generateInvoiceOrDoNothing(company.getId(), new MonthAndYear(month, year));
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void generateInvoiceOrDoNothing(long companyId, MonthAndYear monthAndYear){
        Company company = companyDao.find(companyId);
        List<CourseRequestEntityDTO> requests = courseRequestService.findForMonth(company.getId(), monthAndYear);
        if(requests.size() > 0) {
            InvoiceEntityDTO invoice = createInDb(requests, company,  monthAndYear);
            File pdf = createPdf(monthAndYear, invoice);
            try {
                invoiceMailer.sendInvoiceEmail(pdf, invoice, Locale.getDefault());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private File createPdf(MonthAndYear monthAndYear, InvoiceEntityDTO invoice) {

        Map<String, Object> model = new HashMap<>();
        model.put("invoice", invoice);
        model.put("month", DateFormatSymbols.getInstance().getMonths()[monthAndYear.getMonth() - 1]);
        model.put("logoUrl", properties.getProperty("invoices.logoUrl"));

        String invoicesPath = properties.getProperty("invoices.path");
        Path pdfFileDir = Paths.get(invoicesPath);
        Path pdfFilePath = pdfFileDir.resolve(invoice.getFileName("pdf"));
        File pdfFile = pdfFilePath.toFile();

        File xslTempFile = createTempXslFile(invoice.getFileName("xsl"),
                model, "/velocity/pdf/invoice.vm");

        return generator.generate(xslTempFile, pdfFile);
    }

    public InvoiceEntityDTO createInDb(List<CourseRequestEntityDTO> requests, Company company, MonthAndYear monthAndYear){
        Invoice invoice = invoiceAssembler.assemble(company, requests, monthAndYear);
        invoiceDao.persist(invoice);
        return invoice.toDTO();
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

    @Override
    public void saveAsNewVersion(InvoiceEntityDTO invoice) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
