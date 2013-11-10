package nl.codebasesoftware.produx.domain.assembler;

import nl.codebasesoftware.produx.dao.InvoiceDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Invoice;
import nl.codebasesoftware.produx.domain.InvoiceRecord;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 4-11-13
 * Time: 22:18
 */
@Component
public class InvoiceAssembler {

    private InvoiceDao invoiceDao;
    private ConversionService conversionService;

    @Autowired
    public InvoiceAssembler(InvoiceDao invoiceDao, ConversionService conversionService){
        this.invoiceDao = invoiceDao;
        this.conversionService = conversionService;
    }

    public Invoice assemble(Company company, List<CourseRequestEntityDTO> requests, MonthAndYear monthAndYear){
        Invoice lastInvoice = invoiceDao.findLastForCompany(company.getId());
        Invoice invoice = new Invoice();
        invoice.setCompany(company);
        invoice.setDateCreated(Calendar.getInstance());
        invoice.setForMonth(monthAndYear.getMonth());
        invoice.setForYear(monthAndYear.getYear());

        if(lastInvoice != null){
            invoice.setLastInvoiceNumber(lastInvoice.getNextInvoiceNumber());
            invoice.setSerialNumber(lastInvoice.getNextSerialNumber());
        }

        for (CourseRequestEntityDTO request : requests) {
            InvoiceRecord record = conversionService.convert(request, InvoiceRecord.class);
            invoice.addInvoiceRecord(record);
        }

        return invoice;
    }

}
