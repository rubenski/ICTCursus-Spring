package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;
import nl.codebasesoftware.produx.domain.optionlists.NumberOfMonthsHighlighting;
import nl.codebasesoftware.produx.domain.support.DateRange;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.HighlightCourseFormData;
import nl.codebasesoftware.produx.service.CategoryService;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.HighlightedCourseService;
import nl.codebasesoftware.produx.validator.HighlightedCourseFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 4-5-13
 * Time: 18:46
 */
@Controller
public class AdminHighlightCoursesController {

    private CategoryService categoryService;
    private CompanyService companyService;
    private CourseService courseService;
    private MessageSource messageSource;
    private HighlightedCourseFormValidator highlightedCourseFormValidator;
    private HighlightedCourseService highlightedCourseService;

    @Autowired
    public AdminHighlightCoursesController(CategoryService categoryService, CompanyService companyService, CourseService courseService, MessageSource messageSource,
                                           HighlightedCourseFormValidator highlightedCourseFormValidator, HighlightedCourseService highlightedCourseService) {
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.courseService = courseService;
        this.messageSource = messageSource;
        this.highlightedCourseFormValidator = highlightedCourseFormValidator;
        this.highlightedCourseService = highlightedCourseService;
        this.companyService = companyService;
    }

    @RequestMapping(value = "/admin/highlightcourses/{category}", method = RequestMethod.GET)
    public String showHighlightCoursesLayover(@PathVariable("category") Long categoryId, Model model, Locale locale) {

        Category category = categoryService.findById(categoryId);

        if (category == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("category", category);

        String header = messageSource.getMessage("highlight.your.courses", new Object[]{category.getName()}, locale);

        model.addAttribute("headerText", header);

        return "forms/highlightCourses";
    }

    @RequestMapping(value = "/admin/form/highlightcourses/{categoryId}", method = RequestMethod.GET)
    public String showHighlightCoursesForm(@PathVariable("categoryId") Long categoryId, Model model, Locale locale) {
        setFormData(model, locale, categoryId, new HighlightCourseFormData());
        return "forms/highlightCoursesForm";
    }

    @RequestMapping(value = "/admin/highlightcourses/{categoryId}", method = RequestMethod.POST)
    public String submitHighlightRequest(@PathVariable("categoryId") Long categoryId,
                                         @ModelAttribute("highlightCourseFormData") HighlightCourseFormData highlightCourseFormData,
                                         BindingResult result, Model model, Locale locale) {

        highlightedCourseFormValidator.validate(highlightCourseFormData, result);

        if (!result.hasErrors()) {
            long courseId = highlightCourseFormData.getCourse();
            String startDateParam = highlightCourseFormData.getStartDate();
            int numberOfMonths = highlightCourseFormData.getNumberOfMonths();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyyy");
            Date startDate = null;
            try {
                startDate = sdf.parse(startDateParam);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            highlightedCourseService.addHighlightedCourse(courseId, categoryId, startDate, numberOfMonths);

            model.addAttribute("highlightCourseRequest", highlightCourseFormData);

            return "forms/highlightCoursesFormResult";
        }

        setFormData(model, locale, categoryId, highlightCourseFormData);
        return "forms/highlightCoursesForm";
    }

    private void setFormData(Model model, Locale locale, Long categoryId, HighlightCourseFormData formData) {

        Category category = categoryService.findById(categoryId);
        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();

        DateRange dateRange = highlightedCourseService.findDateRangeForHighlightStart(category.getId());
        List<HighlightedCourseOnCategory> highlightedCoursesForCompany = highlightedCourseService.findCurrentAndFutureHighlightedCoursesForCompany(category.getId(), currentlyLoggedInCompany.getId());


        Calendar startDate = dateRange.getStartDate();
        boolean startDirectlyPossible = isSameDay(startDate, Calendar.getInstance());

        String helpMessageDate = null;
        String helpMessageCourses = messageSource.getMessage("course.highlight.choose.helptext", new Object[]{category.getName()}, locale);

        if (!startDirectlyPossible) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String formattedStartDate = sdf.format(startDate.getTime());
            helpMessageDate = messageSource.getMessage("highlight.courses.start.notdirectly", new Object[]{category.getName(), formattedStartDate}, locale);
        }

        List<Course> nonHighLightedCompanycourses = courseService.findNonHighlightedCoursesForCompanyAndCategory(currentlyLoggedInCompany, category);

        model.addAttribute("hasHighlightedCourses", highlightedCoursesForCompany.size() > 0);
        model.addAttribute("highlightedCoursesForCompany", highlightedCoursesForCompany);
        model.addAttribute("hasEligibleCourses", nonHighLightedCompanycourses.size() > 0);
        model.addAttribute("courses", nonHighLightedCompanycourses);
        model.addAttribute("highlightCourseFormData", formData);
        model.addAttribute("helpMessageCourses", helpMessageCourses);
        model.addAttribute("monthsOptions", NumberOfMonthsHighlighting.NUMBERS);
        model.addAttribute("helpMessageDate", helpMessageDate);
        model.addAttribute("advertiseToday", startDirectlyPossible);
        model.addAttribute("startDay", startDate.get(Calendar.DAY_OF_MONTH));
        model.addAttribute("startMonth", startDate.get(Calendar.MONTH));
        model.addAttribute("startYear", startDate.get(Calendar.YEAR));
        model.addAttribute("endDay", dateRange.getEndDate().get(Calendar.DAY_OF_MONTH));
        model.addAttribute("endMonth", dateRange.getEndDate().get(Calendar.MONTH));
        model.addAttribute("endYear", dateRange.getEndDate().get(Calendar.YEAR));
        model.addAttribute("category", category);
    }

    private boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
}
