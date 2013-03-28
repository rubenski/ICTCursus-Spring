package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.formdata.BindableUsenamePassword;
import nl.codebasesoftware.produx.service.UserInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 8:13
 */
@Controller
public class InvitationActivationController {

    private UserInvitationService userInvitationService;

    @Autowired
    public InvitationActivationController(UserInvitationService userInvitationService) {
        this.userInvitationService = userInvitationService;
    }

    @RequestMapping(value = "/admin/invitation/{securityCode}", method = RequestMethod.GET)
    public String activateAccountForm(@PathVariable("securityCode") String securityCode, Model model) {

        UserInvitation userInvitation = userInvitationService.findBySecurityCode(securityCode);
        if(userInvitation == null){
            model.addAttribute("invitationFound", "1");
        }else{
            model.addAttribute("invitationFound", "0");
        }

        BindableUsenamePassword bindableUsenamePassword = new BindableUsenamePassword();
        bindableUsenamePassword.setUsername(userInvitation.getEmail());
        bindableUsenamePassword.setInvitationId(userInvitation.getId());

        model.addAttribute("bindableUsenamePassword", bindableUsenamePassword);
        model.addAttribute("mainContent", "content/activateAccount");
        return "main";

    }

    @RequestMapping(value = "/admin/invitation/{securityCode}", method = RequestMethod.POST)
    public String activateAccountSubmit(@ModelAttribute("usernamePassword") BindableUsenamePassword usernamePassword,
                                        BindingResult result, Model model){


        return "";
    }


}
