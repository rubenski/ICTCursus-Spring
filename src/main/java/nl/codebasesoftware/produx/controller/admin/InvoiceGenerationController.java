package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.InvoiceService;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * User: rvanloen
 * Date: 30-10-13
 * Time: 21:53
 */
@Controller
public class InvoiceGenerationController {

    private InvoiceService invoiceService;
    private CompanyService companyService;

    @Autowired
    public InvoiceGenerationController(InvoiceService invoiceService, CompanyService companyService){
        this.invoiceService = invoiceService;
        this.companyService = companyService;
    }

    @RequestMapping(value = "/admin/invoice/generate/batch/{month}/{year}")
    public void generateInvoiceBatchWebCall(@PathVariable("month") int month, @PathVariable("year") int year){
        List<CompanyEntityDTO> allCompanies = companyService.findAll();

        for (CompanyEntityDTO company : allCompanies) {
            invoiceService.generateInvoiceOrDoNothing(company.getId(), new MonthAndYear(month, year));
        }
    }

    public void generateInvoiceBatch(){

    }

    @RequestMapping(value = "/admin/invoice/generate/{company}/{month}/{year}")
    public void create(@PathVariable("company")int companyId, @PathVariable("month") int month,
                       @PathVariable("year") int year, HttpServletResponse response){

        invoiceService.generateInvoiceOrDoNothing(companyId, new MonthAndYear(month, year));

        try {
            response.getWriter().write("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
