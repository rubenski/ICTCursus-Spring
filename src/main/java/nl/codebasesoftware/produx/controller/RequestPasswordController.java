package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.formdata.BindableForgotPassword;
import nl.codebasesoftware.produx.net.mail.Mailer;
import nl.codebasesoftware.produx.validator.RequestPasswordFormValidator;
import org.apache.commons.mail.EmailException;
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
    private ApplicationContext applicationContext;

    @Autowired
    public RequestPasswordController(RequestPasswordFormValidator validator) {
        this.validator = validator;
    }

    @RequestMapping(value = "/login/requestpassword", method = RequestMethod.GET)
    public String createPasswordForm(Model model) {
        model.addAttribute("mainContent", "forms/requestPassword");
        model.addAttribute("forgotPassword", new BindableForgotPassword());
        return "main";
    }

    @RequestMapping(value = "/login/requestpassword", method = RequestMethod.POST)
    public String submitPasswordForm(@ModelAttribute("forgotPassword") BindableForgotPassword forgotPassword, Model model, Locale locale, BindingResult result) {
        validator.validate(forgotPassword, result);

        if (result.hasErrors()) {
            model.addAttribute("mainContent", "forms/requestPassword");
        } else {
            model.addAttribute("mainContent", "content/empty");
            model.addAttribute("mainMessage", applicationContext.getMessage("password.sent", null, locale));

            try {
                Mailer.sendHtmlEmail();
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }

        return "main";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
