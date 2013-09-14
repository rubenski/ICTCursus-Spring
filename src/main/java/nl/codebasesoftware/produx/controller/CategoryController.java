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
import nl.codebasesoftware.produx.search.criteria.filter.NormalFilter;
import nl.codebasesoftware.produx.search.result.SearchResult;
import nl.codebasesoftware.produx.service.*;
import nl.codebasesoftware.produx.service.business.SearchQueryProcessor;
import nl.codebasesoftware.produx.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String showFrontPage(@PathVariable("categoryUrlName") String categoryUrlName, Model model) throws ProduxServiceException {
        return process(model, categoryUrlName, null, 0);
    }

    @RequestMapping(value = "/{categoryUrlName}/{filters}", method = RequestMethod.GET)
    public String showFilteredResultPage(@PathVariable("categoryUrlName") String categoryUrlName,
                                         @PathVariable("filters") String filters,
                                         Model model) throws ProduxServiceException {
        return process(model, categoryUrlName, filters, 0);
    }

    @RequestMapping(value = "/{categoryUrlName}/{facets}/{p:[0-9]+}", method = RequestMethod.GET)
    public String showUnfilteredResultPage(@PathVariable("categoryUrlName") String categoryUrlName,
                                           @PathVariable("filters") String filters,
                                           @PathVariable("p") Integer p,
                                           Model model) throws ProduxServiceException {
        return process(model, categoryUrlName, filters, p);
    }

    private String process(Model model, String categoryUrlName, String filters, int page) throws ProduxServiceException {

        // TODO: fix performance
        long start = System.currentTimeMillis();
        Category category = categoryService.findByUrlTitle(categoryUrlName);
        long end = System.currentTimeMillis();
        LOG.debug("1: " + (end - start));

        long start1a = System.currentTimeMillis();
        CategoryEntityDTO cat = category.toDTO();
        long end1a = System.currentTimeMillis();
        LOG.debug("000: " + (end1a - start1a));

        if (category == null) {
            throw new ResourceNotFoundException();
        }

        long start1 = System.currentTimeMillis();
        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        long end1 = System.currentTimeMillis();
        LOG.debug("lll: " + (end1 - start1));

        long start2 = System.currentTimeMillis();
        List<Course> companyCoursesForCategory = new ArrayList<>();
        if (currentlyLoggedInCompany != null) {
            companyCoursesForCategory = courseService.findCoursesForCompanyAndCategory(category.getId(), currentlyLoggedInCompany.getId());
        }
        long end2 = System.currentTimeMillis();
        LOG.debug("bla2: " + (end2 - start2));

        long start3 = System.currentTimeMillis();
        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setEmptyRightColumn(model);
        pageBlockService.setAuthentication(model);
        long end3 = System.currentTimeMillis();
        LOG.debug("bla: " + (end3 - start3));

        long start4 = System.currentTimeMillis();
        int resultsPerPage = properties.getSearchResultsPerPage();
        long end4 = System.currentTimeMillis();
        LOG.debug("ppp: " + (end4 - start4));

        long start5 = System.currentTimeMillis();
        RangeFacetField priceFacet = new RangeFacetField("price", 0, 300000, 50000, FacetSortingBehavior.NATURAL_ORDER);
        priceFacet.addOtherBehavior(RangeFacetOtherBehavior.AFTER);
        priceFacet.addExcludedFilter("_price");

        NormalFacetField regionFacet = new NormalFacetField("regions", FacetSortingBehavior.NATURAL_ORDER);
        regionFacet.addExcludedFilter("_regions");
        regionFacet.setMinCount(1);

        long end5 = System.currentTimeMillis();
        LOG.debug("aaa: " + (end5 - start5));

        long start6 = System.currentTimeMillis();
        NormalFilter categoryFilter = new NormalFilter("category", cat.getSolrValue());

        SearchCriteria criteria = new SearchCriteria.Builder()
                .addFilter(categoryFilter)
                .addFilters(SearchQueryProcessor.stringToFilters(filters))
                .addFacetField(priceFacet)
                .addFacetField(regionFacet)
                .setStart(page * resultsPerPage)
                .setRows(resultsPerPage)
                .build();
        long end6 = System.currentTimeMillis();
        LOG.debug("bbb: " + (end6 - start6));

        // TODO: fix performance
        long start7 = System.currentTimeMillis();
        SearchResult searchResult = searchService.findCategoryCourses(criteria, cat);
        long end7 = System.currentTimeMillis();
        LOG.debug("fff: " + (end7 - start7));

        // Throw a 404 when someone tries to access a paging page that doesn't exist
        if (searchResult.getCourses().size() == 0 && page > 0) {
            throw new ResourceNotFoundException();
        }

        long start8 = System.currentTimeMillis();
        List<ListingCourseDTO> highlightedCourses = courseService.findHighlightedCourses(category.getId());
        long end8 = System.currentTimeMillis();
        LOG.debug("ggg: " + (end8 - start8));


        long start9 = System.currentTimeMillis();
        List<ArticleEntityDTO> categoryArticles = articleService.findByCategory(category.getId());
        long end9 = System.currentTimeMillis();
        LOG.debug("cat articles: " + (end9 - start9));


        long start10 = System.currentTimeMillis();
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
        long end10 = System.currentTimeMillis();
        LOG.debug(end10 - start10);

        return "main";
    }


}


