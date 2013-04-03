package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.BindableUserProfile;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.RolesAndRightService;
import nl.codebasesoftware.produx.service.UserInvitationService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 17-3-13
 * Time: 18:01
 */
@Controller
public class AdminUserProfileController {


    private UserProfileService userProfileService;
    private CompanyService companyService;
    private MessageSource messageSource;
    private ConversionService conversionService;
    private RolesAndRightService rolesAndRightService;
    private UserInvitationService invitationService;

    @Autowired
    public AdminUserProfileController(UserProfileService userProfileService,
                                      CompanyService companyService,
                                      MessageSource messageSource,
                                      ConversionService conversionService,
                                      RolesAndRightService rolesAndRightService,
                                        UserInvitationService invitationService) {
        this.userProfileService = userProfileService;
        this.companyService = companyService;
        this.messageSource = messageSource;
        this.conversionService = conversionService;
        this.rolesAndRightService = rolesAndRightService;
        this.invitationService = invitationService;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String home(Model model, Locale locale) {

        String headerText = messageSource.getMessage("admin.sections.users", new Object[]{}, locale);
        Company loggedInCompany = companyService.getCurrentlyLoggedInCompany();
        List<UserProfile> profiles = userProfileService.findByCompany(loggedInCompany.getId());
        List<UserInvitation> invitations = invitationService.findByInviter(CurrentUser.get().getId());

        model.addAttribute("numberOfInvitations", invitations.size());
        model.addAttribute("numberOfOtherUsers", profiles.size());
        model.addAttribute("profiles", profiles);
        model.addAttribute("invitations", invitations);
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "content/adminUserProfiles");

        return "adminMain";
    }


    @RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
    public String saveUser(@PathVariable("id") Long id, Model model, Locale locale) {

        UserProfile userProfile = userProfileService.findById(id);
        String headerStartText = messageSource.getMessage("user.edit.header.start", new Object[]{}, locale);
        String headerText = String.format("%s %s", headerStartText, userProfile.getFullNameInformal());

        if (userProfile == null) {
            throw new ResourceNotFoundException();
        }

        BindableUserProfile bindableUserProfile = conversionService.convert(userProfile, BindableUserProfile.class);

        model.addAttribute("assignableRoles", rolesAndRightService.findUserAssignableRoles());
        model.addAttribute("rolesEditable", 1);
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "forms/userprofile");
        model.addAttribute("userProfile", bindableUserProfile);
        return "adminMain";
    }


}
