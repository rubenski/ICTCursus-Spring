package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.BindableForgotPassword;
import nl.codebasesoftware.produx.net.mail.PasswordMailer;
import nl.codebasesoftware.produx.service.PageBlockService;
import nl.codebasesoftware.produx.service.SecurityService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.util.SecurityUtil;
import nl.codebasesoftware.produx.validator.RequestPasswordFormValidator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * User: rvanloen
 * Date: 7-11-12
 * Time: 17:02
 */
@Controller
public class RequestPasswordController implements ApplicationContextAware {

    private RequestPasswordFormValidator validator;
    private UserProfileService userProfileService;
    private PasswordMailer passwordMailer;
    private SecurityService securityService;
    private PageBlockService pageBlockService;
    private ApplicationContext applicationContext;

    @Autowired
    public RequestPasswordController(RequestPasswordFormValidator validator, UserProfileService userProfileService, PasswordMailer passwordMailer,
                                     SecurityService securityService, PageBlockService pageBlockService) {
        this.validator = validator;
        this.userProfileService = userProfileService;
        this.passwordMailer = passwordMailer;
        this.securityService = securityService;
        this.pageBlockService = pageBlockService;
    }

    @RequestMapping(value = "/login/requestpassword", method = RequestMethod.GET)
    public String createPasswordForm(Model model) {
        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setEmptyRightColumn(model);
        model.addAttribute("mainContent", "forms/requestPassword");
        model.addAttribute("forgotPassword", new BindableForgotPassword());
        model.addAttribute("title", "ICT Cursus: wachtwoord vergeten");
        return "main";
    }

    @RequestMapping(value = "/login/requestpassword", method = RequestMethod.POST)
    public String submitPasswordForm(@ModelAttribute("forgotPassword") BindableForgotPassword forgotPassword, Model model, Locale locale, BindingResult result) {


        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setEmptyRightColumn(model);

        validator.validate(forgotPassword, result);

        if (result.hasErrors()) {
            model.addAttribute("mainContent", "forms/requestPassword");
        } else {

            UserProfileEntityDTO profile = userProfileService.findByEmail(forgotPassword.getEmail());
            String randomPassword = securityService.getRandomPassword();
            passwordMailer.sendPasswordEmail(profile, randomPassword, locale);
            updatePassword(profile, randomPassword);

            model.addAttribute("mainContent", "content/empty");
            model.addAttribute("mainMessage", applicationContext.getMessage("password.sent", null, locale));

        }

        model.addAttribute("title", "ICT Cursus: wachtwoord vergeten");

        return "main";
    }

    private void updatePassword(UserProfileEntityDTO profile, String password) {
        String randomPasswordHash = SecurityUtil.createShaHash(password);
        profile.setPasswordHash(randomPasswordHash);
        userProfileService.update(profile);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
