package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.PageBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: rvanloen
 * Date: 5-10-13
 * Time: 22:59
 */
@Controller
public class CompanyController {

    private CompanyService companyService;
    private CourseService courseService;

    @Autowired
    public CompanyController(CompanyService companyService, CourseService courseService) {
        this.companyService = companyService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public String show(@PathVariable(value = "id") Long id , Model model){
        CompanyEntityDTO company = companyService.findById(id);
        List<CourseEntityDTO> courseEntityDTOs = courseService.findByCompanyId(id);
        model.addAttribute("mainContent", "content/company");
        model.addAttribute("company", company);
        model.addAttribute("courses", courseEntityDTOs);
        model.addAttribute("hasCourses", courseEntityDTOs.size() > 0);
        model.addAttribute("title", "Cursussen en bedrijfsprofiel van " + company.getName());
        model.addAttribute("rightColumn", "components/companypagerightcolumn");
        model.addAttribute("broadView", true);

        return "main";
    }
}
