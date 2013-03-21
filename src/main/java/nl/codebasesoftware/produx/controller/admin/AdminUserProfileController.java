package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    public AdminUserProfileController(UserProfileService userProfileService, CompanyService companyService, MessageSource messageSource) {
        this.userProfileService = userProfileService;
        this.companyService = companyService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String home(Model model, Locale locale){
        String headerText = messageSource.getMessage("admin.sections.users", new Object[]{}, locale);
        Company loggedInCompany = companyService.getCurrentlyLoggedInCompany();
        List<UserProfile> profiles = userProfileService.findByCompany(loggedInCompany.getId());
        model.addAttribute("numberOfOtherUsers", profiles.size());
        model.addAttribute("profiles", profiles);
        model.addAttribute("headerText", headerText);
        model.addAttribute("mainContent", "content/adminUserProfiles");
        return "adminMain";
    }

    @RequestMapping(value = "/admin/users/invite", method = RequestMethod.POST)
    public String createUserInvitationForm(@ModelAttribute("userInvitation") BindableUserInvitation userInvitation, Model model, Locale locale){

        String message = messageSource.getMessage("admin.sections.users", new Object[]{}, locale);

        model.addAttribute("userInvitation", userInvitation);
        model.addAttribute("headerText", message);
        model.addAttribute("mainContent", "forms/userInvitation");
        return "adminMain";
    }

    @RequestMapping(value = "/admin/users/invite", method = RequestMethod.GET)
    public String createUserForm(Model model, Locale locale){
        String message = messageSource.getMessage("admin.sections.inviteuser", new Object[]{}, locale);
        model.addAttribute("headerText", message);
        model.addAttribute("userInvitation", new BindableUserInvitation());
        model.addAttribute("mainContent", "forms/userInvitation");
        return "adminMain";
    }
}
