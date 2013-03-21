package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.service.*;
import nl.codebasesoftware.produx.validator.CourseFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: rvanloen
 * Date: 10-11-12
 * Time: 21:16
 */
@Controller
@RequestMapping(value = "/admin/course")
public class AdminCourseController {

    private CourseService courseService;
    private RegionService regionService;
    private CategoryService categoryService;
    private CourseFormValidator validator;
    private CompanyService companyService;
    private ConversionService conversionService;
    private OptionService optionService;

    @Autowired
    public AdminCourseController(CourseService courseService,
                                 RegionService regionService,
                                 CategoryService categoryService,
                                 CourseFormValidator validator,
                                 CompanyService companyService,
                                 ConversionService conversionService,
                                 OptionService optionService) {
        this.courseService = courseService;
        this.regionService = regionService;
        this.categoryService = categoryService;
        this.validator = validator;
        this.companyService = companyService;
        this.conversionService = conversionService;
        this.optionService = optionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model){
        model.addAttribute("mainContent", "content/adminCourses");

        UserProfile userProfile = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyService.findByUserProfile(userProfile);
        List<Course> courses = courseService.findByCompany(company);
        model.addAttribute("courses", courses);
        model.addAttribute("numberOfCourses", courses.size());

        return "adminMain";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getCourseForm(@PathVariable("id") Long id, Model model) {

        Company loggedInCompany = companyService.getCurrentlyLoggedInCompany();
        List<Course> companyCourses = courseService.findByCompany(loggedInCompany);

        model.addAttribute("mainContent", "forms/editCourse");
        Course course = courseService.findFull(id);

        if (course == null || !companyCourses.contains(course)) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("bindableCourse", conversionService.convert(course, BindableCourse.class));
        addDataToModel(model);

        return "adminMain";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String newCourse(Model model) {

        BindableCourse bindableCourse = new BindableCourse();
        addDataToModel(model);
        model.addAttribute("bindableCourse", bindableCourse);
        model.addAttribute("mainContent", "forms/editCourse");
        addDataToModel(model);

        return "adminMain";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute("bindableCourse") BindableCourse bindableCourse, BindingResult result, Model model) {

        validator.validate(bindableCourse, result);

        if (!result.hasErrors()) {
            Course course = courseService.saveOrUpdate(bindableCourse);
            return "redirect:/admin/course/" + course.getId();
        }

        addDataToModel(model);
        model.addAttribute("mainContent", "forms/editCourse");
        return "adminMain";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public String updateCourse(@ModelAttribute("bindableCourse") BindableCourse bindableCourse, BindingResult result, Model model) {
        validator.validate(bindableCourse, result);

        String courseValid = "false";

        if (!result.hasErrors()) {
            courseService.saveOrUpdate(bindableCourse);
            courseValid = "true";
        }

        model.addAttribute("mainContent", "forms/editCourse");
        model.addAttribute("courseValid", courseValid);
        addDataToModel(model);
        return "adminMain";

    }

    private void addDataToModel(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Region> allRegions = regionService.findAll();
        List<Time> courseTimes = courseService.findCourseTimes();
        List<OptionCategory> optionCategories = optionService.findAllOptionCategories();
        model.addAttribute("times", courseTimes);
        model.addAttribute("allRegions", allRegions);
        model.addAttribute("categories", categories);
        model.addAttribute("optionCategories", optionCategories);
    }
}
