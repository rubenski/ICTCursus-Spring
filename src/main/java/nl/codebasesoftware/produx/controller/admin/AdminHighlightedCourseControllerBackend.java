package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.HighlightedCoursePeriod;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.HighlightedCoursePeriodService;
import nl.codebasesoftware.produx.service.PageBlockService;
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 29-11-13
 * Time: 23:05
 */
@Controller
public class AdminHighlightedCourseControllerBackend {

    private HighlightedCoursePeriodService highlightedCoursePeriodService;
    private CompanyService companyService;
    private MessageSource messageSource;


    @Autowired
    public AdminHighlightedCourseControllerBackend(HighlightedCoursePeriodService highlightedCoursePeriodService,
                                                   CompanyService companyService,
                                                   MessageSource messageSource) {
        this.highlightedCoursePeriodService = highlightedCoursePeriodService;
        this.companyService = companyService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "admin/courses/highlighted", method = RequestMethod.GET)
    public String showHighlightedCourses(Model model, Locale locale){

        CompanyEntityDTO company = companyService.getCurrentlyLoggedInCompany();
        List<HighlightedCoursePeriod> highlightedForCompany = highlightedCoursePeriodService.findHighlightedForCompany(company.getId());


        model.addAttribute("highlightedCourses", highlightedForCompany);
        model.addAttribute("headerText", messageSource.getMessage("advertised.courses", new Object[]{}, locale));
        model.addAttribute("mainContent", "content/adminhighlightedcourses");
        model.addAttribute("numberOfCourses", highlightedForCompany.size());

        return "adminMain";

    }

}
