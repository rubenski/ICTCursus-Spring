package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping(value = "/manage/home")
public class ManagementHomeController {

    private CourseService userService;
    private CompanyService companyService;

    @Autowired
    public ManagementHomeController(CourseService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("mainContent", "content/managementHome");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserProfile profile = (UserProfile) auth.getPrincipal();
        Company company = companyService.findByUserProfile(profile);


        return "main";
    }



}
