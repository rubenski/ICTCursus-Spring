package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SessionData sessionData;

    @Autowired
    public ManagementHomeController(CourseService userService, CompanyService companyService, SessionData sessionData) {
        this.userService = userService;
        this.companyService = companyService;
        this.sessionData = sessionData;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("mainContent", "content/managementHome");

        // sessionData.company is null here!

        return "main";
    }
}
