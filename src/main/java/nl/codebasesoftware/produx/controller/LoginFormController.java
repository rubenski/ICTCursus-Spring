package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Roles;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.validator.LoginFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 15-8-12
 * Time: 11:24
 */
@Controller
@RequestMapping(value = "/login")
public class LoginFormController {

    private UserProfileService userProfileService;
    private LoginFormValidator validator;

    @Autowired
    public LoginFormController(UserProfileService userProfileService, LoginFormValidator validator) {
        this.userProfileService = userProfileService;
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("mainContent", "forms/login");
        return "main";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("userProfile") UserProfile userProfile, BindingResult result, Model model) {
        validator.validate(userProfile, result);

        if (result.hasErrors()) {
            model.addAttribute("mainContent", "forms/login");
            return "main";
        } else {



            if(userProfile.hasRole(Roles.ADMIN)){
                model.addAttribute("mainContent", "content/adminhome");
            }else if(userProfile.hasRole(Roles.COMPANY)){
                model.addAttribute("mainContent", "content/companyhome");
            }


        }
        return "main";
    }

}
