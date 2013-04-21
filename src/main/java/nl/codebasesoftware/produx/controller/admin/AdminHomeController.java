package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.support.RoleName;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * User: rvanloen
 * Date: 31-10-12
 * Time: 11:48
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {


    public AdminHomeController() {

    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {

        UserProfile profile = CurrentUser.get();

        if(profile.hasRole(RoleName.SYS_ADMIN)){
            return "redirect:/admin/sys/";
        }

        model.addAttribute("mainContent", "content/adminHome");
        return "adminMain";
    }
}
