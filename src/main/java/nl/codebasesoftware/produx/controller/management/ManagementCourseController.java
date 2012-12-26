package nl.codebasesoftware.produx.controller.management;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Region;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.service.CategoryService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: rvanloen
 * Date: 10-11-12
 * Time: 21:16
 */
@Controller
@RequestMapping(value = "/manage/course/{id}")
public class ManagementCourseController {

    private CourseService courseService;
    private RegionService regionService;
    private CategoryService categoryService;

    @Autowired
    public ManagementCourseController(CourseService courseService, RegionService regionService, CategoryService categoryService) {
        this.courseService = courseService;
        this.regionService = regionService;
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id, Model model) {

        model.addAttribute("mainContent", "forms/editCourse");

        Course course = courseService.findFull(id);
        List<Category> categories = categoryService.findAll();
        List<Region> allRegions = regionService.findAll();

        if(course == null){
            throw new ResourceNotFoundException();
        }

        model.addAttribute("bindableCourse", course.toBindableCourse());
        model.addAttribute("allRegions", allRegions);
        model.addAttribute("categories", categories);
        return "managementMain";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@ModelAttribute("bindableCourse") BindableCourse course, Model model) {
        courseService.update(course);
        model.addAttribute("updated", 1);
        return get(course.getId(), model);
    }




}
