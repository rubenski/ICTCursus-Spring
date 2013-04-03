package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.BindableUserProfile;
import nl.codebasesoftware.produx.service.UserInvitationService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.validator.UserProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
 * Time: 19:57
 */
@Controller
public class AccountActivationController {

    private UserInvitationService userInvitationService;
    private ConversionService conversionService;
    private UserProfileValidator userProfileValidator;
    private UserProfileService userProfileService;

    @Autowired
    public AccountActivationController(UserInvitationService userInvitationService, ConversionService conversionService,
                                       UserProfileValidator userProfileValidator, UserProfileService userProfileService) {
        this.userInvitationService = userInvitationService;
        this.conversionService = conversionService;
        this.userProfileValidator = userProfileValidator;
        this.userProfileService = userProfileService;
    }


    @RequestMapping(value = "/users/activate/{securityCode}", method = RequestMethod.GET)
    public String activateAccountForm(@PathVariable("securityCode") String securityCode, Model model) {

        UserInvitation userInvitation = userInvitationService.findBySecurityCode(securityCode);
        UserProfile existingProfile = userProfileService.findByEmail(userInvitation.getEmail());

        String accountForEmailExists = "0";

        if (userInvitation == null) {
            throw new ResourceNotFoundException();
        }

        if (userInvitation.isActivated() || existingProfile != null) {
            accountForEmailExists = "1";
        }

        BindableUserProfile userProfile = conversionService.convert(userInvitation, BindableUserProfile.class);

        model.addAttribute("userProfile", userProfile);
        model.addAttribute("accountForEmailExists", accountForEmailExists);
        model.addAttribute("mainContent", "forms/activateAccount");

        return "main";
    }

    @RequestMapping(value = "/users/activate/{securityCode}", method = RequestMethod.POST)
    public String submitAccountActivation(@ModelAttribute("userProfile") BindableUserProfile profile,
                                          BindingResult result, Model model) {

        userProfileValidator.validate(profile, result);

        if(!result.hasErrors()){
            userInvitationService.activateProfile(profile);
            model.addAttribute("activationSucceeded", 1);
        } else {
            model.addAttribute("activationSucceeded", 0);
        }

        model.addAttribute("mainContent", "forms/activateAccount");
        model.addAttribute("userProfile", profile);


        return "main";
    }

}
