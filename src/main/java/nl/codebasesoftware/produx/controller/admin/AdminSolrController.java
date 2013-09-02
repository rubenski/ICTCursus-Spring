package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.search.solrquery.SolrQuery;
import nl.codebasesoftware.produx.search.solrquery.queryitems.FacetFieldParameter;
import nl.codebasesoftware.produx.search.solrquery.queryitems.FacetFieldRangeParameter;
import nl.codebasesoftware.produx.search.solrquery.queryitems.SearchPhraseParameter;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.SolrService;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<Course> courses = courseService.findByCompany(company);
        List<CourseEntityDTO> updatableCourses = new ArrayList<>();
        for (Course course : courses) {
            updatableCourses.add(course.toDTO());
        }
        return solrService.addOrUpdate(updatableCourses);
    }
}
