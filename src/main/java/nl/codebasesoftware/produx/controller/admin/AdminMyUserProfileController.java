package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import nl.codebasesoftware.produx.formdata.BindableMyUserProfile;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.validator.MyUserProfileValidator;
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
    private MyUserProfileValidator myUserProfileValidator;

    @Autowired
    public AdminMyUserProfileController(ConversionService conversionService, MessageSource messageSource,
                                        UserProfileService userProfileService, MyUserProfileValidator myUserProfileValidator) {
        this.conversionService = conversionService;
        this.messageSource = messageSource;
        this.userProfileService = userProfileService;
        this.myUserProfileValidator = myUserProfileValidator;
    }

    @RequestMapping(value = {"/admin/myprofile", "/admin/sys/myprofile"}, method = RequestMethod.GET)
    public String myProfileForm(Model model, Locale locale){
        UserProfile userProfile = userProfileService.findById(CurrentUser.get().getId());
        String headerText = messageSource.getMessage("user.edit.myprofile", new Object[]{}, locale);

        BindableMyUserProfile bindableMyUserProfile = conversionService.convert(userProfile, BindableMyUserProfile.class);

        model.addAttribute("statusEditable", 0);
        model.addAttribute("rolesEditable", 0);
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "forms/myuserprofile");
        model.addAttribute("myUserProfile", bindableMyUserProfile);

        return userProfile.hasRole(RoleName.SYS_ADMIN) ? "sysAdminMain" : "adminMain";
    }

    @RequestMapping(value = {"/admin/myprofile", "/admin/sys/myprofile"}, method = RequestMethod.POST)
    public String submitMyProfileForm(@ModelAttribute("myUserProfile")BindableMyUserProfile profile, BindingResult result,
                                      Model model, Locale locale){
        UserProfile userProfile = userProfileService.findById(profile.getId());
        String headerText = messageSource.getMessage("user.edit.myprofile", new Object[]{}, locale);
        String valid = "false";
        myUserProfileValidator.validate(profile, result);

        if(!result.hasErrors()){
            valid = "true";
            userProfileService.update(profile);
        }

        model.addAttribute("valid", valid);
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "forms/myuserprofile");
        model.addAttribute("myUserProfile", profile);

        return userProfile.hasRole(RoleName.SYS_ADMIN) ? "sysAdminMain" : "adminMain";
    }
}
