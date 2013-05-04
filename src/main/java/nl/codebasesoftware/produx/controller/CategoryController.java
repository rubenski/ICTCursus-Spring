package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.service.CategoryService;
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
 * Date: 16-9-12
 * Time: 12:03
 */
@Controller
@RequestMapping("/c/{categoryUrlName}")
public class CategoryController {

    private CourseService courseService;
    private CategoryService categoryService;
    private PageBlockService pageBlockService;

    @Autowired
    public CategoryController(CourseService courseService, CategoryService categoryService, PageBlockService pageBlockService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
        this.pageBlockService = pageBlockService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setup(@PathVariable("categoryUrlName") String categoryUrlName, Model model){

        Category category = categoryService.findByName(categoryUrlName);

        if(category == null){
            throw new ResourceNotFoundException();
        }

        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setAuthentication(model);

        List<Course> courses = courseService.findCourses(category.getId());

        model.addAttribute("title", "Cursussen " + category.getName() + " - ICT Cursus");
        model.addAttribute("courses", courses);
        model.addAttribute("category", category);
        model.addAttribute("mainContent", "content/category");
        return "main";
    }
}


