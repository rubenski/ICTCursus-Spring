package nl.codebasesoftware.produx.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 17-4-13
 * Time: 0:27
 */
@Controller
public class SysAdminHomeController {

    @RequestMapping(value = "/admin/sys", method = RequestMethod.GET)
    public String sysAdminHome(Model model){
        model.addAttribute("mainContent", "sysAdminHome");
        return "sysAdminMain";
    }
}
