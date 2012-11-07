package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.validator.LoginFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * User: rvanloen
 * Date: 15-8-12
 * Time: 11:24
 */
@Controller
public class LoginFormController {

    @Resource(name = "authenticationProvider")
    AuthenticationProvider authenticationProvider;

    private UserProfileService userProfileService;
    private LoginFormValidator validator;

    @Autowired
    public LoginFormController(UserProfileService userProfileService, LoginFormValidator validator) {
        this.userProfileService = userProfileService;
        this.validator = validator;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String createLoginForm(Model model) {
        model.addAttribute("mainContent", "forms/springLogin");
        UserProfile userProfile = new UserProfile();
        model.addAttribute("userProfile", userProfile);
        return "main";
    }



}
