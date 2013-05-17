package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableSysAdminUserProfile;
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 12-5-13
 * Time: 22:16
 */
@Controller
public class SysAdminUserProfileController {

    private UserProfileService userProfileService;
    private MessageSource messageSource;
    private ConversionService conversionService;

    @Autowired
    public SysAdminUserProfileController(UserProfileService userProfileService, MessageSource messageSource,
                                         ConversionService conversionService) {
        this.userProfileService = userProfileService;
        this.messageSource = messageSource;
        this.conversionService = conversionService;
    }



    @RequestMapping(value = "/admin/sys/userprofiles")
    public String getUserProfiles(Model model, Locale locale){
        List<UserProfile> userProfiles = userProfileService.findAll();
        model.addAttribute("userProfiles", userProfiles);
        model.addAttribute("mainContent", "content/sysAdminUserProfiles");
        model.addAttribute("headerText", messageSource.getMessage("sysadmin.userprofiles", new Object[]{}, locale));
        return "sysAdminMain";
    }

    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    @RequestMapping(value = "/admin/sys/userprofile/{id}", method = RequestMethod.GET)
    public String getUserProfile(@PathVariable("id") Long id, Model model, Locale locale){

        UserProfile userProfile = userProfileService.findWithCompany(id);

        BindableSysAdminUserProfile bindableSysAdminUserProfile = new BindableSysAdminUserProfile();
        bindableSysAdminUserProfile.setId(userProfile.getId());
        bindableSysAdminUserProfile.setEnabled(userProfile.isEnabled());
        bindableSysAdminUserProfile.setEmail(userProfile.getEmail());
        bindableSysAdminUserProfile.setCompanyId(userProfile.getCompany().getId());

        setDefaultFormData(model, locale, bindableSysAdminUserProfile, userProfile);

        return "sysAdminMain";
    }

    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    @RequestMapping(value = "/admin/sys/userprofile/{id}", method = RequestMethod.POST)
    public String updateUserProfile(@ModelAttribute("userProfile") BindableSysAdminUserProfile bindableSysAdminUserProfile, Model model, Locale locale){

        UserProfile userProfile = userProfileService.findWithCompany(bindableSysAdminUserProfile.getId());

        String valid = "false";

        if(bindableSysAdminUserProfile.getRemove() == 1){
            userProfileService.removeProfile(bindableSysAdminUserProfile.getId());
            return "redirect:/admin/sys/userprofiles";
        }else {
            userProfileService.update(bindableSysAdminUserProfile);
            valid = "true";
        }

        setDefaultFormData(model, locale, bindableSysAdminUserProfile, userProfile);

        model.addAttribute("valid", valid);

        return "sysAdminMain";
    }

    private void setDefaultFormData(Model model, Locale locale, BindableSysAdminUserProfile bindableSysAdminUserProfile, UserProfile userProfile){
        String headerText = messageSource.getMessage("user.edit.header.start", new Object[]{}, locale) + " " + userProfile.getFullNameInformal();
        model.addAttribute("userProfile", bindableSysAdminUserProfile);
        model.addAttribute("mainContent", "forms/sysadminuserprofile");
        model.addAttribute("statuses", userProfileService.getProfileStatuses(locale));
        model.addAttribute("headerText", headerText);
        model.addAttribute("sysadminUserProfileForm", true);
    }
}
