package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.service.CategoryService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.helpers.CourseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 12:03
 */
@Controller
@RequestMapping("/c/**")
public class CategoryController {

    private CourseService courseService;
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{categoryUrlName}")
    public String setup(@PathVariable("categoryUrlName") String categoryUrlName, Model model){
        CourseFilter filter = new CourseFilter();

        Category category = categoryService.findByName(categoryUrlName);

        if(category == null){
            throw new ResourceNotFoundException();
        }

        model.addAttribute("category", category);
        model.addAttribute("mainContent", "content/category");
        return "main";
    }
}


