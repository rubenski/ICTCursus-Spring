package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseService;
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
@RequestMapping(value = "/admin/home")
public class AdminHomeController {

    private CompanyService companyService;
    private CourseService courseService;

    @Autowired
    public AdminHomeController(CompanyService companyService, CourseService courseService) {
        this.companyService = companyService;
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {

        model.addAttribute("mainContent", "content/adminHome");
        return "adminMain";
    }
}
