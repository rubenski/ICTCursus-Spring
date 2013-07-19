package nl.codebasesoftware.produx.controller;


import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.search.SolrQueryBuilder;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.SearchService;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 12:46
 */
@Controller
public class SearchController {

    private SearchService searchService;
    private CourseService courseService;

    @Autowired
    public SearchController(SearchService searchService, CourseService courseService) {
        this.searchService = searchService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("for") String terms, Model model) {

        SolrQueryBuilder builder = new SolrQueryBuilder();
        QueryResponse response = searchService.search(terms);

        List<Long> ids = new ArrayList<Long>();
        for (SolrDocument solrDocument : response.getResults()) {
            String idString =  (String) solrDocument.get("id");
            long id = Long.parseLong(idString);
            ids.add(id);
        }

        List<Course> courses = courseService.findBasic(ids);

        FacetField categoryFacet = response.getFacetField("category");
        FacetField tagsFacet = response.getFacetField("tags");
        FacetField regionsFacet = response.getFacetField("regions");
        FacetField incompanyFacet = response.getFacetField("incompany");
        FacetField certificateFacet = response.getFacetField("certificate");

        FacetField priceFacet = response.getFacetField("price");

        model.addAttribute("categoryFacets", categoryFacet);
        model.addAttribute("regionFacets", regionsFacet);
        model.addAttribute("tagFacets", tagsFacet);
        model.addAttribute("incompanyFacets", incompanyFacet);
        model.addAttribute("certificateFacets", certificateFacet);
        model.addAttribute("priceFacets", priceFacet);

        model.addAttribute("response", response);
        model.addAttribute("courses", courses);
        model.addAttribute("terms", terms);
        model.addAttribute("mainContent", "content/searchResults");
        return "main";
    }

}
