package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.PasswordFormData;
import nl.codebasesoftware.produx.service.SecurityService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.validator.NewPasswordFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * User: rvanloen
 * Date: 20-11-13
 * Time: 23:56
 */
@Controller
public class AdminMyPasswordController {

    private MessageSource messageSource;
    private NewPasswordFormValidator validator;
    private UserProfileService userProfileService;
    private SecurityService securityService;

    @Autowired
    public AdminMyPasswordController(MessageSource messageSource, NewPasswordFormValidator validator, UserProfileService userProfileService, SecurityService securityService) {
        this.messageSource = messageSource;
        this.validator = validator;
        this.userProfileService = userProfileService;
        this.securityService = securityService;
    }

    @RequestMapping(value = "/admin/myprofile/password", method = RequestMethod.GET)
    public String myPassword(Model model, Locale locale) {
        model.addAttribute("passwordForm", new PasswordFormData());
        model.addAttribute("mainContent", "forms/passwordform");
        model.addAttribute("headerText", messageSource.getMessage("passwordform.header", new Object[]{}, locale));
        return "adminMain";
    }

    @RequestMapping(value = "/admin/myprofile/password", method = RequestMethod.POST)
    public String saveMyPassword(@ModelAttribute("passwordForm") PasswordFormData formData, BindingResult result, Model model) {

        validator.validate(formData, result);
        model.addAttribute("valid", "false");

        if(!result.hasErrors()){
            UserProfileEntityDTO user = userProfileService.getCurrentlyLoggedInUser();
            user.setPasswordHash(securityService.getHash(formData.getNewPassword1()));
            userProfileService.update(user);
            model.addAttribute("valid", "true");
        }

        model.addAttribute("mainContent", "forms/passwordform");
        return "adminMain";
    }
}
