package nl.codebasesoftware.produx.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;


/**
 * User: rvanloen
 * Date: 12-12-13
 * Time: 13:32
 */
@Controller
public class AdminFaqController {

    private MessageSource messageSource;

    @Autowired
    public AdminFaqController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/admin/faq", method = RequestMethod.GET)
    public String showFaq(Model model, Locale locale){
        model.addAttribute("headerText", messageSource.getMessage("faq.title", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminfaq");
        return "adminMain";
    }
}
