package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.OtherUserProfileFormData;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.RolesAndRightService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.validator.OtherUserProfileFormValidator;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 17-3-13
 * Time: 18:01
 */
@Controller
public class AdminOtherUserProfileController {


    private UserProfileService userProfileService;
    private CompanyService companyService;
    private MessageSource messageSource;
    private ConversionService conversionService;
    private RolesAndRightService rolesAndRightService;
    private OtherUserProfileFormValidator otherUserProfileFormValidator;


    @Autowired
    public AdminOtherUserProfileController(UserProfileService userProfileService,
                                           CompanyService companyService,
                                           MessageSource messageSource,
                                           ConversionService conversionService,
                                           RolesAndRightService rolesAndRightService,
                                           OtherUserProfileFormValidator otherUserProfileFormValidator) {
        this.userProfileService = userProfileService;
        this.companyService = companyService;
        this.messageSource = messageSource;
        this.conversionService = conversionService;
        this.rolesAndRightService = rolesAndRightService;
        this.otherUserProfileFormValidator = otherUserProfileFormValidator;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String home(Model model, Locale locale) {

        String headerText = messageSource.getMessage("admin.sections.users", new Object[]{}, locale);
        Company loggedInCompany = companyService.getCurrentlyLoggedInCompany();
        List<UserProfile> profiles = userProfileService.findOthersInCompany(loggedInCompany.getId(), CurrentUser.get());

        model.addAttribute("numberOfOtherUsers", profiles.size());
        model.addAttribute("profiles", profiles);
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "content/adminUserProfiles");

        return "adminMain";
    }


    @RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") Long id, Model model, Locale locale) {

        UserProfile userProfile = userProfileService.findById(id);

        if (userProfile == null || !companyService.getCurrentlyLoggedInCompany().equals(userProfile.getCompany())) {
            throw new ResourceNotFoundException();
        }

        String headerStartText = messageSource.getMessage("user.edit.header.start", new Object[]{}, locale);
        String headerText = String.format("%s %s", headerStartText, userProfile.getFullNameInformal());

        OtherUserProfileFormData otherUserProfile = conversionService.convert(userProfile, OtherUserProfileFormData.class);

        addStatuses(model, locale);

        model.addAttribute("assignableRoles", rolesAndRightService.findUserAssignableRoles());
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "forms/otheruserprofile");
        model.addAttribute("otherUserProfile", otherUserProfile);

        return "adminMain";
    }

    @RequestMapping(value = "/admin/users/{id}", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("otherUserProfile") OtherUserProfileFormData otherUserProfile, Model model, Locale locale, BindingResult result) {

        String valid = "false";
        UserProfile userProfile = userProfileService.findById(otherUserProfile.getId());
        String headerStartText = messageSource.getMessage("user.edit.header.start", new Object[]{}, locale);
        String headerText = String.format("%s %s", headerStartText, userProfile.getFullNameInformal());
        addStatuses(model, locale);

        otherUserProfileFormValidator.validate(otherUserProfile, result);

        if(!result.hasErrors()){
            valid = "true";
            userProfileService.update(otherUserProfile);
        }

        model.addAttribute("valid", valid);
        model.addAttribute("assignableRoles", rolesAndRightService.findUserAssignableRoles());
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "forms/otheruserprofile");
        model.addAttribute("otherUserProfile", otherUserProfile);

        return "adminMain";
    }


    private void addStatuses(Model model, Locale locale) {
        String active = messageSource.getMessage("generic.message.active", new Object[]{}, locale);
        String inactive = messageSource.getMessage("generic.message.inactive", new Object[]{}, locale);
        List<ProfileStatus> statuses = new ArrayList<ProfileStatus>();
        statuses.add(new ProfileStatus(false, inactive));
        statuses.add(new ProfileStatus(true, active));
        model.addAttribute("statuses", statuses);
    }


    private static class ProfileStatus {
        private boolean enabled;
        private String name;

        private ProfileStatus(boolean enabled, String name) {
            this.name = name;
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public String getName() {
            return name;
        }
    }


}
