package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.UserInvitationService;
import nl.codebasesoftware.produx.validator.UserInvitationValidator;
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
 * Date: 21-3-13
 * Time: 21:43
 */
@Controller
public class AdminInvitationController {

    private MessageSource messageSource;
    private UserInvitationService userInvitationService;
    private UserInvitationValidator userInvitationValidator;
    private CompanyService companyService;

    @Autowired
    public AdminInvitationController(MessageSource messageSource,
                                     UserInvitationService userInvitationService,
                                     UserInvitationValidator userInvitationValidator,
                                     CompanyService companyService) {
        this.messageSource = messageSource;
        this.userInvitationService = userInvitationService;
        this.userInvitationValidator = userInvitationValidator;
        this.companyService = companyService;
    }

    @RequestMapping(value = "/admin/users/invite", method = RequestMethod.POST)
    public String processForm(@ModelAttribute("userInvitation") BindableUserInvitation userInvitation, Model model, Locale locale,
                                           BindingResult result) {
        setInvitationData(model, locale);
        userInvitationValidator.validate(userInvitation, result);

        String invitationValid = "false";

        if (!result.hasErrors()) {
            userInvitationService.inviteUserForCurrentCompany(userInvitation);
            return "redirect:/admin/users";
        }

        model.addAttribute("invitationValid", invitationValid);

        return "adminMain";
    }



    @RequestMapping(value = "/admin/users/invite", method = RequestMethod.GET)
    public String createUserForm(Model model, Locale locale) {
        setInvitationData(model, locale);
        model.addAttribute("userInvitation", new BindableUserInvitation());
        return "adminMain";
    }

    private void setInvitationData(Model model, Locale locale) {
        String message = messageSource.getMessage("admin.sections.inviteuser", new Object[]{}, locale);
        model.addAttribute("headerText", message);
        model.addAttribute("mainContent", "forms/userInvitation");
    }
}
