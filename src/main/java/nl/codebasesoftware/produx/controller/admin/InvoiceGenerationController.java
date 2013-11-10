package nl.codebasesoftware.produx.controller.admin;

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

/**
 * User: rvanloen
 * Date: 30-10-13
 * Time: 21:53
 */
@Controller
public class InvoiceGenerationController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceGenerationController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @RequestMapping(value = "/admin/invoice/{invoiceNumber")
    public void get(@PathVariable("company")int companyId, @PathVariable("month") int month, HttpServletResponse response){

        response.setContentType("application/pdf");
        /*
        try {
            response.getOutputStream().write(pdfImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
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
