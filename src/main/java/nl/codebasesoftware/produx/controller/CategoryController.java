package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.search.*;
import nl.codebasesoftware.produx.service.*;
import nl.codebasesoftware.produx.util.Properties;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 12:03
 */
@Controller
public class CategoryController {

    private static Logger LOG = Logger.getLogger(CategoryController.class);

    private CourseService courseService;
    private CategoryService categoryService;
    private PageBlockService pageBlockService;
    private CompanyService companyService;
    private ArticleService articleService;
    private SearchService searchService;
    private Properties properties;

    @Autowired
    public CategoryController(CourseService courseService,
                              CategoryService categoryService,
                              PageBlockService pageBlockService,
                              CompanyService companyService,
                              ArticleService articleService,
                              SearchService searchService,
                              Properties properties) {
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

    @RequestMapping(value = "/{categoryUrlName}/{facets:.+}/{p:[0-9]+}", method = RequestMethod.GET)
    public String showUnfilteredResultPage(@PathVariable("categoryUrlName") String categoryUrlName,
                                           @PathVariable("filters") String filters,
                                           @PathVariable("p") Integer p,
                                           Model model) throws ProduxServiceException {
        return process(model, categoryUrlName, filters, p);
    }

    private String process(Model model, String categoryUrlName, String filters, int page) throws ProduxServiceException {

        Category category = categoryService.findByUrlTitle(categoryUrlName);
        CategoryEntityDTO cat = category.toDTO();

        if(category == null){
            throw new ResourceNotFoundException();
        }

        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();

        List<Course> companyCoursesForCategory  = new ArrayList<>();
        if(currentlyLoggedInCompany != null){
            companyCoursesForCategory = courseService.findCoursesForCompanyAndCategory(category.getId(), currentlyLoggedInCompany.getId());
        }

        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setEmptyRightColumn(model);
        pageBlockService.setAuthentication(model);

        long start = System.currentTimeMillis();

        int resultsPerPage = properties.getSearchResultsPerPage();

        RangeFacetField rangeFacetField = new RangeFacetField("price", 0, 300000, 50000, FacetSortingBehavior.COUNT);
        rangeFacetField.addOtherBehavior(RangeFacetOtherBehavior.AFTER);

        NormalFilter categoryFilter = new NormalFilter("category", cat.getSolrValue());


        SearchCriteria criteria = new SearchCriteria.Builder()
                .addFilter(categoryFilter)
                .addFilters(stringToFilters(filters))
                .addFacetField(rangeFacetField)
                .setStart(page * resultsPerPage)
                .setRows(resultsPerPage)
                .build();

        String url = categoryService.generateUrl(cat, criteria);

        SearchResult searchResult = searchService.findCategoryCourses(criteria, page, url);

        // Throw a 404 when someone tries to access a paging page that doesn't exist
        if(searchResult.getCourses().size() == 0 && page > 0){
            throw new ResourceNotFoundException();
        }

        List<ListingCourseDTO> highlightedCourses = courseService.findHighlightedCourses(category.getId());
        List<ArticleEntityDTO> categoryArticles = articleService.findByCategory(category.getId());

        LOG.debug("time : " + (System.currentTimeMillis() - start));

        model.addAttribute("articles", categoryArticles);
        model.addAttribute("showLightboxLink", companyCoursesForCategory.size() > 0);
        model.addAttribute("showHighlighted", page == 0);
        model.addAttribute("title", "Cursussen " + category.getName() + " - ICT Cursus");
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("highlighted", highlightedCourses);
        model.addAttribute("category", category);
        model.addAttribute("mainContent", "content/category");
        model.addAttribute("rightColumn", "content/articlelisting");
        model.addAttribute("facetedSearch", true);
        return "main";
    }


    private List<Filter> stringToFilters(String filters){

        if(filters == null || filters.length() == 0){
            return Collections.EMPTY_LIST;
        }

        List<Filter> filterList = new ArrayList<>();

        String[] filterStrings = filters.split("_");

        for (String filterString : filterStrings) {
            String[] nameValue = filterString.split(":");
            String name = nameValue[0];
            String values = nameValue[1];


            if(name.equals("price")){

                String[] priceRanges = values.split(",");
                List<Range> ranges = new ArrayList<>();
                for (String priceRange : priceRanges) {
                    String[] rangeValues = priceRange.split("-");
                    int from = Integer.parseInt(rangeValues[0]);
                    int to = Integer.parseInt(rangeValues[1]);
                    ranges.add(new Range<>(from, to));
                }

                MultiValueRangeFilter priceFilter = new MultiValueRangeFilter(name, ranges);

                filterList.add(priceFilter);
            }
        }

        return filterList;

    }


}


