package nl.codebasesoftware.produx.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 23:29
 */
@Controller
public class AdminInvoiceController {

    @Autowired
    public AdminInvoiceController() {
    }

    @RequestMapping(value = "/admin/invoices", method = RequestMethod.GET)
    public String showInvoices(){

        return "adminMain";
    }
}
