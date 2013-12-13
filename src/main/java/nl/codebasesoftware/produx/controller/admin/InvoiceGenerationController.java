package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceBatchEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.InvoiceService;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    public InvoiceGenerationController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @RequestMapping(value = "/admin/invoice/generate/batch/{month}/{year}")
    public String generateInvoiceBatchWebCall(Model model, @PathVariable("month") int month, @PathVariable("year") int year) throws ProduxServiceException {
        InvoiceBatchEntityDTO invoiceBatch = invoiceService.runInvoiceBatch(month, year);
        model.addAttribute("mainContent", "content/invoicebatchresult");
        model.addAttribute("invoiceBatch", invoiceBatch);
        return "adminMain";
    }
}
