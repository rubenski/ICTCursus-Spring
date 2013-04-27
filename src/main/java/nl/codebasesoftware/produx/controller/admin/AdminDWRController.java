package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.service.impl.ArithmeticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 26-4-13
 * Time: 1:17
 */
@Controller
public class AdminDWRController {


    private ArithmeticServiceImpl arithmeticService;

    @Autowired
    public AdminDWRController(ArithmeticServiceImpl arithmeticService) {
        this.arithmeticService = arithmeticService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAjaxAddPage(Model model) {
        return "dwrtest";
    }
}
