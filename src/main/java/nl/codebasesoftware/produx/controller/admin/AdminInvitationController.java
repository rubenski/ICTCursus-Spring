package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import nl.codebasesoftware.produx.service.RolesAndRightService;
import nl.codebasesoftware.produx.service.UserInvitationService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.validator.UserInvitationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    private RolesAndRightService rolesAndRightService;



    @Autowired
    public AdminInvitationController(MessageSource messageSource,
                                     UserInvitationService userInvitationService,
                                     UserInvitationValidator userInvitationValidator,
                                     RolesAndRightService rolesAndRightService) {
        this.messageSource = messageSource;
        this.userInvitationService = userInvitationService;
        this.userInvitationValidator = userInvitationValidator;
        this.rolesAndRightService = rolesAndRightService;
    }

    @RequestMapping(value = "/admin/invitations", method = RequestMethod.GET)
    public String getInvitations(Model model, Locale locale){
        String headerText = messageSource.getMessage("admin.sections.invitations", new Object[]{}, locale);

        List<UserInvitation> invitations = userInvitationService.findByInviter(CurrentUser.get().getId());

        model.addAttribute("numberOfInvitations", invitations.size());
        model.addAttribute("invitations", invitations);
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "content/adminUserInvitations");

        return "adminMain";
    }

    @RequestMapping(value = "/admin/invitations/invite", method = RequestMethod.POST)
    public String processForm(@ModelAttribute("userInvitation") BindableUserInvitation userInvitation, Model model, Locale locale,
                              BindingResult result, HttpServletRequest request) {

        setHeader(model, locale);
        setInvitationData(model);
        userInvitationValidator.validate(userInvitation, result);
        List<Role> selectedRoles = rolesAndRightService.findByIds(userInvitation.getRoles());

        // We are handling two different screens in the same method here, so we need
        // a way to figure out which screen is posting.
        if (!userInvitation.isConfirmationScreen()) {
            if (!result.hasErrors()) {
                userInvitation.setConfirmationScreen(true);
                model.addAttribute("invitation", userInvitation);
                model.addAttribute("selectedRoles", selectedRoles);
                model.addAttribute("mainContent", "forms/confirmInvitation");
                return "adminMain";
            }
        } else {
            String goback = request.getParameter("goback");
            if (goback == null) {
                try {
                    userInvitationService.inviteUserForCurrentCompany(userInvitation);
                } catch (ProduxServiceException e) {
                    e.printStackTrace();
                }
                return "redirect:/admin/users";
            }
        }

        return "adminMain";
    }

    @RequestMapping(value = "/admin/invitations/invite", method = RequestMethod.GET)
    public String createUserForm(Model model, Locale locale) {
        setInvitationData(model);
        setHeader(model, locale);
        model.addAttribute("userInvitation", new BindableUserInvitation());
        return "adminMain";
    }

    @RequestMapping(value = "/admin/invitations/revoke/{id}")
    public String revokeInvitation(@PathVariable("id") Long invitationdId, Model model) {

        userInvitationService.removeInvitation(invitationdId);

        return "redirect:/admin/users";
    }


    private void setInvitationData(Model model) {
        model.addAttribute("companyRoles", rolesAndRightService.findUserAssignableRoles());
        model.addAttribute("mainContent", "forms/userInvitation");
    }

    private void setHeader(Model model, Locale locale) {
        String message = messageSource.getMessage("admin.sections.inviteuser", new Object[]{}, locale);
        model.addAttribute("headerText", message);
    }
}
