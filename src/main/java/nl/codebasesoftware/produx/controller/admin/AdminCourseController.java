package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.service.*;
import nl.codebasesoftware.produx.validator.CourseFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 10-11-12
 * Time: 21:16
 */
@Controller
public class AdminCourseController {

    private CourseService courseService;
    private RegionService regionService;
    private CategoryService categoryService;
    private CourseFormValidator validator;
    private CompanyService companyService;
    private ConversionService conversionService;
    private OptionService optionService;
    private MessageSource messageSource;

    @Autowired
    public AdminCourseController(CourseService courseService,
                                 RegionService regionService,
                                 CategoryService categoryService,
                                 CourseFormValidator validator,
                                 CompanyService companyService,
                                 ConversionService conversionService,
                                 OptionService optionService,
                                 MessageSource messageSource) {
        this.courseService = courseService;
        this.regionService = regionService;
        this.categoryService = categoryService;
        this.validator = validator;
        this.companyService = companyService;
        this.conversionService = conversionService;
        this.optionService = optionService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/admin/courses", method = RequestMethod.GET)
    public String get(Model model, Locale locale) {
        UserProfile userProfile = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyService.findByUserProfile(userProfile);
        List<Course> courses = courseService.findByCompany(company);
        model.addAttribute("mainContent", "content/adminCourses");
        setCoursesScreenData(model, locale, courses);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/sys/courses", method = RequestMethod.GET)
    public String showSysadminCourses(Model model, Locale locale) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("mainContent", "content/sysAdminCourses");
        setCoursesScreenData(model, locale, courses);
        return "sysAdminMain";
    }

    @RequestMapping(value = "/admin/course/{id}", method = RequestMethod.GET)
    public String getCourseForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes, Locale locale) {

        String isValidNewCourse = (String) redirectAttributes.getFlashAttributes().get("valid");
        if(isValidNewCourse != null && isValidNewCourse.equals("true")){
            model.addAttribute("valid", isValidNewCourse);
        }


        Company loggedInCompany = companyService.getCurrentlyLoggedInCompany();
        List<Course> companyCourses = courseService.findByCompany(loggedInCompany);

        model.addAttribute("mainContent", "forms/editCourse");
        Course course = courseService.findFull(id);

        if (course == null || !companyCourses.contains(course)) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("bindableCourse", conversionService.convert(course, BindableCourse.class));
        addDataToModel(model, locale);

        return "adminMain";
    }

    @RequestMapping(value = "/admin/sys/course/{id}", method = RequestMethod.GET)
    public String getSysAdminCourseForm(@PathVariable("id") Long id, Model model, Locale locale) {
        model.addAttribute("mainContent", "forms/editCourse");
        Course course = courseService.findFull(id);
        model.addAttribute("bindableCourse", conversionService.convert(course, BindableCourse.class));
        addDataToModel(model, locale);
        return "adminMain";
    }

    @RequestMapping(value = "/admin/course/add", method = RequestMethod.GET)
    public String newCourse(Model model, Locale locale) {

        BindableCourse bindableCourse = new BindableCourse();
        model.addAttribute("bindableCourse", bindableCourse);
        model.addAttribute("mainContent", "forms/editCourse");
        addDataToModel(model, locale);

        return "adminMain";
    }

    @RequestMapping(value = "/admin/course/add", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute("bindableCourse") BindableCourse bindableCourse, BindingResult result,
                            RedirectAttributes redirectAttributes, Model model, Locale locale) {

        validator.validate(bindableCourse, result);

        if (!result.hasErrors()) {
            Course course = courseService.save(bindableCourse);
            bindableCourse.setPublishable(true);
            redirectAttributes.addFlashAttribute("valid", "true");
            return "redirect:/admin/course/" + course.getId();
        }

        addDataToModel(model, locale);
        model.addAttribute("mainContent", "forms/editCourse");
        return "adminMain";
    }

    @RequestMapping(value = {"/admin/sys/course/{id}", "/admin/course/{id}"}, method = RequestMethod.POST)
    public String updateCourse(@ModelAttribute("bindableCourse") BindableCourse bindableCourse, BindingResult result, Model model, Locale locale) {
        validator.validate(bindableCourse, result);

        String valid = "false";

        if (!result.hasErrors()) {
            courseService.save(bindableCourse);
            bindableCourse.setPublishable(true);
            valid = "true";
        }

        model.addAttribute("mainContent", "forms/editCourse");
        model.addAttribute("valid", valid);
        addDataToModel(model, locale);
        return "adminMain";
    }


    private void addDataToModel(Model model, Locale locale) {
        String headerText = messageSource.getMessage("admin.sections.courses", new Object[]{}, locale);
        List<Category> categories = categoryService.findAll();
        List<Region> allRegions = regionService.findAll();
        List<Time> courseTimes = courseService.findCourseTimes();
        List<OptionCategory> optionCategories = optionService.findAllOptionCategories();
        model.addAttribute("times", courseTimes);
        model.addAttribute("allRegions", allRegions);
        model.addAttribute("categories", categories);
        model.addAttribute("optionCategories", optionCategories);
        model.addAttribute("headerText", headerText);
    }

    private void setCoursesScreenData(Model model, Locale locale, List<Course> courses) {
        String headerText = messageSource.getMessage("admin.sections.courses", new Object[]{}, locale);
        model.addAttribute("courses", courses);
        model.addAttribute("numberOfCourses", courses.size());
        model.addAttribute("headerText", headerText);
    }

}
