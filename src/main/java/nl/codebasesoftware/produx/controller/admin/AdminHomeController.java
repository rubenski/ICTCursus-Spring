package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;


/**
 * User: rvanloen
 * Date: 31-10-12
 * Time: 11:48
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {


    private MessageSource messageSource;

    @Autowired
    public AdminHomeController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, Locale locale) {

        UserProfileEntityDTO profile = CurrentUser.get();

        if (profile.hasRole(RoleName.SYS_ADMIN)) {
            return "redirect:/admin/sys/";
        }
        model.addAttribute("headerText", messageSource.getMessage("admin.home.header", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminHome");
        return "adminMain";
    }
}
