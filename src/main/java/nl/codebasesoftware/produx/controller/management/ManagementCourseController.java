package nl.codebasesoftware.produx.controller.management;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Region;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.service.CategoryService;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.RegionService;
import nl.codebasesoftware.produx.validator.CourseFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

/**
 * User: rvanloen
 * Date: 10-11-12
 * Time: 21:16
 */
@Controller
@RequestMapping(value = "/manage/course/")
public class ManagementCourseController {

    private CourseService courseService;
    private RegionService regionService;
    private CategoryService categoryService;
    private CourseFormValidator validator;
    private CompanyService companyService;


    @Autowired
    public ManagementCourseController(CourseService courseService, RegionService regionService, CategoryService categoryService,
                                      CourseFormValidator validator, CompanyService companyService) {
        this.courseService = courseService;
        this.regionService = regionService;
        this.categoryService = categoryService;
        this.validator = validator;
        this.companyService = companyService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getCourseForm(@PathVariable("id") Long id, Model model) {

        Company loggedInCompany = companyService.getCurrentlyLoggedInCompany();
        List<Course> companyCourses = courseService.findByCompany(loggedInCompany);

        model.addAttribute("mainContent", "forms/editCourse");
        Course course = courseService.findFull(id);

        if(course == null || !companyCourses.contains(course)){
            throw new ResourceNotFoundException();
        }

        model.addAttribute("bindableCourse", course.toBindableCourse());

        addDataToModel(model);
        return "managementMain";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public String updateCourse(@ModelAttribute("bindableCourse") BindableCourse bindableCourse, BindingResult result, Model model) {
        validator.validate(bindableCourse, result);

        String courseValid = "false";

        if (!result.hasErrors()) {
            courseService.update(bindableCourse);
            courseValid = "true";
        }

        model.addAttribute("mainContent", "forms/editCourse");
        model.addAttribute("courseValid", courseValid);
        addDataToModel(model);
        return "managementMain";

    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newCourse(Model model) {
        BindableCourse bindableCourse = new BindableCourse();

        model.addAttribute("bindableCourse", bindableCourse);
        model.addAttribute("mainContent", "forms/editCourse");
        addDataToModel(model);

        return "managementMain";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute("bindableCourse") BindableCourse bindableCourse, BindingResult result, SessionStatus status, Model model) {

        validator.validate(bindableCourse, result);
        if (!result.hasErrors()) {
            Course course = courseService.insert(bindableCourse);
            return "redirect:/manage/course/" + course.getId();
        }
        model.addAttribute("mainContent", "forms/editCourse");
        return "managementMain";
    }

    private void addDataToModel(Model model){
        List<Category> categories = categoryService.findAll();
        List<Region> allRegions = regionService.findAll();
        model.addAttribute("allRegions", allRegions);
        model.addAttribute("categories", categories);
    }


}
