package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:08
 */
@Controller
@RequestMapping("/")
public class HomepageController {

    private CourseService courseService;

    @Autowired
    public HomepageController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setup(Model model){
        List<Category> firstLevelCategories = courseService.findFirstLevelCategories();
        model.addAttribute("categories", firstLevelCategories);
        model.addAttribute("mainContent", "content/home");
        return "main";
    }
}
