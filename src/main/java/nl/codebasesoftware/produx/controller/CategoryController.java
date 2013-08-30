package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.search.SearchCriteria;
import nl.codebasesoftware.produx.search.SearchResult;
import nl.codebasesoftware.produx.service.*;
import nl.codebasesoftware.produx.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String showFrontPage(@PathVariable("categoryUrlName") String categoryUrlName, Model model){
        return process(model, categoryUrlName, 0);
    }

    @RequestMapping(value = "/{categoryUrlName/{p:[0-9]+}", method = RequestMethod.GET)
    public String showResultPage(@PathVariable("categoryUrlName") String categoryUrlName, @PathVariable("p") Integer page, Model model){
        return process(model, categoryUrlName, page);
    }

    private String process(Model model, String categoryUrlName, int page){
        Category category = categoryService.findByUrlTitle(categoryUrlName);

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

        LOG.debug("time solr: " + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();

        SearchResult searchResult = findWithSolr(category, page);
        List<ListingCourseDTO> highlightedCourses = courseService.findHighlightedCourses(category.getId());
        List<ArticleEntityDTO> categoryArticles = articleService.findByCategory(category.getId());

        LOG.debug("time db: " + (System.currentTimeMillis() - start1));

        model.addAttribute("articles", categoryArticles);
        model.addAttribute("showLightboxLink", companyCoursesForCategory.size() > 0);
        model.addAttribute("showHighlighted", page == 0);
        model.addAttribute("title", "Cursussen " + category.getName() + " - ICT Cursus");
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("highlighted", highlightedCourses);
        model.addAttribute("category", category);
        model.addAttribute("mainContent", "content/category");
        return "main";
    }




    private SearchResult findWithSolr(Category category, int page) {

        String property = properties.getProperty("courses.per.page");
        int coursesPerPage = Integer.parseInt(property);

        SearchCriteria criteria = new SearchCriteria.Builder()
                .addCategory(category.toDTO())
                .setOffset(page * coursesPerPage)
                .setRows(coursesPerPage)
                .build();

        SearchResult result = null;

        try {
            result = searchService.findCourses(criteria);
        } catch (ProduxServiceException e) {
            e.printStackTrace();
        }

        return result;
    }
}


