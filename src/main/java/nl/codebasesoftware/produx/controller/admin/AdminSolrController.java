package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.SolrService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 30-1-13
 * Time: 15:03
 */
@Controller
public class AdminSolrController {


    private ConversionService conversionService;
    private SolrService solrService;
    private CourseService courseService;
    private CompanyService companyService;

    private static Logger LOG = Logger.getLogger(AdminSolrController.class);

    @Autowired
    public AdminSolrController(CourseService courseService, ConversionService conversionService, SolrService solrService
            , CompanyService companyService) {
        this.courseService = courseService;
        this.conversionService = conversionService;
        this.solrService = solrService;
        this.companyService = companyService;
    }

    @RequestMapping(value = "/admin/updatesolr")
    public String updateSolr(Model model) {
        List<CourseEntityDTO> indexableCourses = courseService.findIndexableCourses();
        solrService.addOrUpdate(indexableCourses);
        model.addAttribute("mainContent", "content/solr");
        model.addAttribute("updatedCourses", indexableCourses);
        model.addAttribute("numberOfCourses", indexableCourses.size());
        return "adminMain";
    }

    @RequestMapping(value = "/admin/solr/updatecompany/{companyId}", method = RequestMethod.POST)
    @ResponseBody
    public int updateCompanyCourses(@PathVariable("companyId") long companyId){
        Company company = companyService.findById(companyId);
        List<CourseEntityDTO> courses = courseService.findByCompany(company);
        List<CourseEntityDTO> updatableCourses = new ArrayList<>();
        for (CourseEntityDTO course : courses) {
            updatableCourses.add(course);
        }
        return solrService.addOrUpdate(updatableCourses);
    }
}
