package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.PageBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 15-8-12
 * Time: 11:24
 */
@Controller
public class LoginFormController {

    private PageBlockService pageBlockService;

    @Autowired
    public LoginFormController(PageBlockService pageBlockService) {
        this.pageBlockService = pageBlockService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String createLoginForm(Model model) {
        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setEmptyRightColumn(model);
        model.addAttribute("mainContent", "forms/springLogin");
        UserProfile userProfile = new UserProfile();
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("title", "ICT Cursus : inloggen");
        model.addAttribute("broadView", true);
        return "main";
    }


}
