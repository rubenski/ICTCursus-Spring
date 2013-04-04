package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableUserProfile;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.validator.UserProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * User: rvanloen
 * Date: 4-4-13
 * Time: 1:14
 */
@Controller
public class AdminMyUserProfileController {

    private ConversionService conversionService;
    private MessageSource messageSource;
    private UserProfileService userProfileService;
    private UserProfileValidator userProfileValidator;

    @Autowired
    public AdminMyUserProfileController(ConversionService conversionService, MessageSource messageSource,
                                        UserProfileService userProfileService, UserProfileValidator userProfileValidator) {
        this.conversionService = conversionService;
        this.messageSource = messageSource;
        this.userProfileService = userProfileService;
        this.userProfileValidator = userProfileValidator;
    }

    @RequestMapping(value = "/admin/myprofile", method = RequestMethod.GET)
    public String myProfileForm(Model model, Locale locale){
        UserProfile userProfile = CurrentUser.get();
        String headerText = messageSource.getMessage("user.edit.myprofile", new Object[]{}, locale);

        BindableUserProfile bindableUserProfile = conversionService.convert(userProfile, BindableUserProfile.class);

        model.addAttribute("statusEditable", 0);
        model.addAttribute("rolesEditable", 0);
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "forms/userprofile");
        model.addAttribute("userProfile", bindableUserProfile);

        return "adminMain";
    }

    @RequestMapping(value = "/admin/myprofile", method = RequestMethod.POST)
    public String submitMyProfileForm(@ModelAttribute("userProfile")BindableUserProfile profile, BindingResult result,
                                      Model model, Locale locale){

        String headerText = messageSource.getMessage("user.edit.myprofile", new Object[]{}, locale);

        userProfileValidator.validate(profile, result);

        if(!result.hasErrors()){
            userProfileService.update(conversionService.convert(profile, UserProfile.class));
        }


        model.addAttribute("statusEditable", 0);
        model.addAttribute("rolesEditable", 0);
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "forms/userprofile");
        model.addAttribute("userProfile", profile);

        return "adminMain";
    }
}
