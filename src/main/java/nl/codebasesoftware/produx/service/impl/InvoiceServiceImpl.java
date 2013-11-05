package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.InvoiceDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Invoice;
import nl.codebasesoftware.produx.domain.assembler.InvoiceAssembler;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.service.CourseRequestService;
import nl.codebasesoftware.produx.service.InvoiceService;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.util.pdf.PdfGenerator;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private InvoiceAssembler invoiceAssembler;


    @Autowired
    public InvoiceServiceImpl(CourseRequestService courseRequestService, VelocityEngine velocityEngine,
                              InvoiceDao invoiceDao, Properties properties, CompanyDao companyDao,
                              PdfGenerator generator, ConversionService conversionService,
                              InvoiceAssembler invoiceAssembler) {
        this.courseRequestService = courseRequestService;
        this.velocityEngine = velocityEngine;
        this.invoiceDao = invoiceDao;
        this.properties = properties;
        this.companyDao = companyDao;
        this.generator = generator;
        this.invoiceAssembler = invoiceAssembler;
    }

    @Override
    @Transactional(readOnly = false)
    public File generateNewVersionForMonth(long companyId, int month) {


        List<CourseRequestEntityDTO> requests = courseRequestService.findForMonth(companyId, month);
        Company company = companyDao.find(companyId);
        InvoiceEntityDTO invoice = saveNewInvoice(requests, company);

        Map<String, Object> model = new HashMap<>();
        model.put("invoice", invoice);
        model.put("month", DateFormatSymbols.getInstance().getMonths()[month - 1]);
        model.put("logoUrl", properties.getProperty("invoices.logoUrl"));

        File pdfFile = createPdfFile(company);
        File xslTempFile = createTempXslFile(createFileName(company, "xsl"), model, "/velocity/pdf/invoice.vm");

        return generator.generate(xslTempFile, pdfFile);
    }

    public InvoiceEntityDTO saveNewInvoice(List<CourseRequestEntityDTO> requests, Company company){
        Invoice invoice = invoiceAssembler.assemble(company, requests);
        invoiceDao.persist(invoice);
        return invoice.toDTO();
    }

    private File createPdfFile(Company company) {
        String invoicesPath = properties.getProperty("invoices.path");
        Path pdfFileDir = Paths.get(invoicesPath);
        Path pdfFilePath = pdfFileDir.resolve(createFileName(company, "pdf"));
        File file = pdfFilePath.toFile();

        return file;
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

    private String createFileName(Company company, String extension) {
        List<Invoice> invoices = invoiceDao.findForCompany(company.getId());
        return String.format("%s-%s.%s", company.getCompanyPrefix(), String.format("%03d", invoices.size() + 1), extension);
    }

    @Override
    public File fromDisk(String invoiceNumber) throws ProduxServiceException {
        String dir = properties.getProperty("invoices.path");
        Path dirPath = Paths.get(dir);
        existsAndReadable(dirPath);
        Path invoiceFilePath = dirPath.resolve(String.format("%s.pdf", invoiceNumber));
        existsAndReadable(invoiceFilePath);
        return invoiceFilePath.toFile();
    }

    public void existsAndReadable(Path path) throws ProduxServiceException {
        if (!Files.exists(path) || Files.isReadable(path)) {
            throw new ProduxServiceException(String.format("Directory %s not found or not readable", path));
        }
    }

    @Override
    public void saveAsNewVersion(InvoiceEntityDTO invoice) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
