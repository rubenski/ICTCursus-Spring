package nl.codebasesoftware.produx.controller.management;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * User: rvanloen
 * Date: 31-10-12
 * Time: 11:48
 */
@Controller
@RequestMapping(value = "/manage/home")
public class ManagementHomeController {

    private CompanyService companyService;
    private CourseService courseService;

    @Autowired
    public ManagementHomeController(CompanyService companyService, CourseService courseService) {
        this.companyService = companyService;
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("mainContent", "content/managementHome");

        UserProfile userProfile = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyService.findByUserProfile(userProfile);
        List<Course> courses = courseService.findByCompany(company);
        model.addAttribute("courses", courses);
        model.addAttribute("numberOfCourses", courses.size());

        return "managementMain";
    }
}
