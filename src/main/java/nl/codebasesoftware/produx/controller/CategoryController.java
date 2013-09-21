package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
import nl.codebasesoftware.produx.search.criteria.facet.FacetSortingBehavior;
import nl.codebasesoftware.produx.search.criteria.facet.NormalFacetField;
import nl.codebasesoftware.produx.search.criteria.facet.RangeFacetField;
import nl.codebasesoftware.produx.search.criteria.facet.RangeFacetOtherBehavior;
import nl.codebasesoftware.produx.search.criteria.filter.Filter;
import nl.codebasesoftware.produx.search.criteria.filter.NormalFilter;
import nl.codebasesoftware.produx.search.result.SearchResult;
import nl.codebasesoftware.produx.service.*;
import nl.codebasesoftware.produx.service.business.FilterFromUrlExtractor;
import nl.codebasesoftware.produx.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 12:03
 */
@Controller
public class CategoryController {

    private static Logger LOG = Logger.getLogger(CategoryController.class);

    private ApplicationContext applicationContext;
    private CourseService courseService;
    private CategoryService categoryService;
    private PageBlockService pageBlockService;
    private CompanyService companyService;
    private ArticleService articleService;
    private SearchService searchService;
    private Properties properties;


    @Autowired
    public CategoryController(ApplicationContext applicationContext,
                              CourseService courseService,
                              CategoryService categoryService,
                              PageBlockService pageBlockService,
                              CompanyService companyService,
                              ArticleService articleService,
                              SearchService searchService,
                              Properties properties) {
        this.applicationContext = applicationContext;
        this.courseService = courseService;
        this.categoryService = categoryService;
        this.pageBlockService = pageBlockService;
        this.companyService = companyService;
        this.articleService = articleService;
        this.searchService = searchService;
        this.properties = properties;
    }

    @RequestMapping(value = "/{categoryUrlName}", method = RequestMethod.GET)
    public String showFrontPage(@PathVariable("categoryUrlName") String categoryUrlName, Model model,
                                HttpServletRequest request) throws ProduxServiceException {
        return process(model, categoryUrlName, null, 0, request);
    }

    @RequestMapping(value = "/{categoryUrlName}/{filters}", method = RequestMethod.GET)
    public String showFilteredResultPage(@PathVariable("categoryUrlName") String categoryUrlName,
                                         @PathVariable("filters") String filters,
                                         Model model,
                                         HttpServletRequest request) throws ProduxServiceException {
        return process(model, categoryUrlName, filters, 0, request);
    }

    @RequestMapping(value = "/{categoryUrlName}/{facets}/{p:[0-9]+}", method = RequestMethod.GET)
    public String showUnfilteredResultPage(@PathVariable("categoryUrlName") String categoryUrlName,
                                           @PathVariable("filters") String filters,
                                           @PathVariable("p") Integer p,
                                           Model model,
                                           HttpServletRequest request) throws ProduxServiceException {
        return process(model, categoryUrlName, filters, p, request);
    }

    private String process(Model model, String categoryUrlName, String filters, int page, HttpServletRequest req) throws ProduxServiceException {

        Category category = categoryService.findByUrlTitle(categoryUrlName);
        CategoryEntityDTO cat = category.toDTO();

        if (category == null) {
            throw new ResourceNotFoundException();
        }

        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();

        List<Course> companyCoursesForCategory = new ArrayList<>();
        if (currentlyLoggedInCompany != null) {
            companyCoursesForCategory = courseService.findCoursesForCompanyAndCategory(category.getId(), currentlyLoggedInCompany.getId());
        }

        pageBlockService.setEmptyRightColumn(model);
        pageBlockService.setAuthentication(model);

        int resultsPerPage = properties.getSearchResultsPerPage();

        List<Filter> filterList = new FilterFromUrlExtractor().stringToFilters(filters);

        RangeFacetField priceFacet = new RangeFacetField("price", 0, 300000, 50000, FacetSortingBehavior.NATURAL_ORDER);
        priceFacet.addOtherBehavior(RangeFacetOtherBehavior.AFTER);
        for (Filter filter : filterList) {
            if(filter.getTag().equals("_price")){
                priceFacet.addExcludedFilter("_price");
            }
        }

        NormalFacetField regionFacet = new NormalFacetField("regions", FacetSortingBehavior.NATURAL_ORDER);
        for (Filter filter : filterList) {
            if(filter.getTag().equals("_regions")){
                regionFacet.addExcludedFilter("_regions");
            }
        }
        regionFacet.setMinCount(1);

        NormalFacetField tagsFacet = new NormalFacetField("tags", FacetSortingBehavior.COUNT);

        NormalFilter categoryFilter = new NormalFilter("category", cat.getSolrValue(), cat.getSolrValue());

        SearchCriteria criteria = new SearchCriteria.Builder()
                .addFilter(categoryFilter)
                .addFilters(filterList)
                .addFacetField(priceFacet)
                .addFacetField(regionFacet)
                .addFacetField(tagsFacet)
                .setStart(page * resultsPerPage)
                .setRows(resultsPerPage)
                .build();


        SearchResult searchResult = searchService.findCategoryCourses(criteria, cat);

        // Throw a 404 when someone tries to access a paging page that doesn't exist
        if (searchResult.getCourses().size() == 0 && page > 0) {
            throw new ResourceNotFoundException();
        }

        List<ListingCourseDTO> highlightedCourses = courseService.findHighlightedCourses(category.getId());
        List<ArticleEntityDTO> categoryArticles = articleService.findByCategory(category.getId());

        model.addAttribute("articles", categoryArticles);
        model.addAttribute("showLightboxLink", companyCoursesForCategory.size() > 0);
        model.addAttribute("showHighlighted", page == 0);
        model.addAttribute("title", "Cursussen " + category.getName() + " - ICT Cursus");
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("highlighted", highlightedCourses);
        model.addAttribute("category", category);
        model.addAttribute("mainContent", "content/category");
        model.addAttribute("rightColumn", "content/articlelisting");
        model.addAttribute("dir", category.getUrlTitle());
        model.addAttribute("facetedSearch", true);

        return "main";
    }


}


