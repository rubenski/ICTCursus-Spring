package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.AccountActivationFormData;
import nl.codebasesoftware.produx.service.UserInvitationService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.validator.AccountActivationFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 19:57
 */
@Controller
public class AccountActivationController {

    private UserInvitationService userInvitationService;
    private ConversionService conversionService;
    private AccountActivationFormValidator formValidator;
    private UserProfileService userProfileService;
    private MessageSource messageSource;

    @Autowired
    public AccountActivationController(UserInvitationService userInvitationService, ConversionService conversionService,
                                       AccountActivationFormValidator formValidator,
                                       UserProfileService userProfileService,
                                       MessageSource messageSource) {
        this.userInvitationService = userInvitationService;
        this.conversionService = conversionService;
        this.formValidator = formValidator;
        this.userProfileService = userProfileService;
        this.messageSource = messageSource;
    }


    @RequestMapping(value = "/users/activate/{securityCode}", method = RequestMethod.GET)
    public String activateAccountForm(@PathVariable("securityCode") String securityCode, Model model, Locale locale) {

        UserInvitation userInvitation = userInvitationService.findBySecurityCode(securityCode);

        String failureMessage = "";

        if (userInvitation == null) {
            failureMessage = messageSource.getMessage("userinvitation.doesntexist", new Object[]{}, locale);
        } else if (userInvitation.isActivated()) {
            failureMessage = messageSource.getMessage("account.already.activated", new Object[]{}, locale);
        } else {
            UserProfile existingProfile = userProfileService.findByEmail(userInvitation.getEmail());
            if (existingProfile != null) {
                failureMessage = messageSource.getMessage("account.already.exists", new Object[]{}, locale);
            }
        }

        AccountActivationFormData accountActivationFormData = conversionService.convert(userInvitation, AccountActivationFormData.class);

        model.addAttribute("failureMessage", failureMessage);
        model.addAttribute("accountActivationFormData", accountActivationFormData);
        model.addAttribute("mainContent", "forms/activateAccount");

        return "main";
    }

    @RequestMapping(value = "/users/activate/{securityCode}", method = RequestMethod.POST)
    public String submitAccountActivation(@ModelAttribute("accountActivationFormData") AccountActivationFormData accountActivationFormData,
                                          BindingResult result, Model model) {

        boolean valid = false;
        formValidator.validate(accountActivationFormData, result);

        if (!result.hasErrors()) {
            valid = true;
            // Check if the user profile exists for if someone resubmitted the request by pressing F5
            UserProfile profile = userProfileService.findByEmail(accountActivationFormData.getEmail());
            if (profile == null) {
                profile = userInvitationService.activateProfile(accountActivationFormData);
            }

            model.addAttribute("userProfile", profile);
            model.addAttribute("mainContent", "content/activationresult");
            return "main";
        }

        model.addAttribute("failureMessage", "");
        model.addAttribute("submitted", true);
        model.addAttribute("valid", valid);
        model.addAttribute("mainContent", "forms/activateAccount");
        model.addAttribute("accountActivationFormData", accountActivationFormData);

        return "main";
    }


}
