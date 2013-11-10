package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.InvoiceService;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 23:29
 */
@Controller
public class AdminInvoiceController {

    private InvoiceService invoiceService;
    private CompanyService companyService;
    private MessageSource messageSource;

    @Autowired
    public AdminInvoiceController(InvoiceService invoiceService, CompanyService companyService, MessageSource messageSource) {
        this.invoiceService = invoiceService;
        this.companyService = companyService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/admin/invoices", method = RequestMethod.GET)
    public String showInvoices(@RequestParam(value = "year", required = false) Integer year,
                               Model model) {

        model.addAttribute("mainContent", "content/admininvoice");
        Company company = companyService.getCurrentlyLoggedInCompany();

        if(year == null){
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
        }

        List<InvoiceEntityDTO> list = invoiceService.findForCompany(company.getId(), year);
        model.addAttribute("invoices", list);
        model.addAttribute("numberOfInvoices", list.size());
        model.addAttribute("headerText", messageSource.getMessage("admin.your.invoices", new Object[]{}, Locale.getDefault()));

        return "adminMain";
    }

    @RequestMapping(value = "/admin/invoices/{id}", method = RequestMethod.GET)
    public void showInvoice(@PathVariable(value = "id") int id, HttpServletResponse response) {

        try {
            response.setContentType("application/pdf");
            File file = invoiceService.fromDisk(id);
            FileInputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();
            int i;
            while((i = inputStream.read()) != -1){
                outputStream.write(i);
            }
        } catch (ProduxServiceException | IOException e) {
            e.printStackTrace();
        }
    }
}
