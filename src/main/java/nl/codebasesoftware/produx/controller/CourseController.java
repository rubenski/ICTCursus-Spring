package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.optionlists.NumberOfParticipants;
import nl.codebasesoftware.produx.domain.optionlists.Prefixes;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import nl.codebasesoftware.produx.service.CourseRequestService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.PageBlockService;
import nl.codebasesoftware.produx.validator.CourseRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

/**
 * User: rvanloen
 * Date: 20-9-12
 * Time: 1:25
 */
@Controller
public class CourseController {

    private CourseService courseService;
    private PageBlockService pageBlockService;
    private CourseRequestValidator courseRequestValidator;
    private CourseRequestService courseRequestService;

    @Autowired
    public CourseController(CourseService courseService, PageBlockService pageBlockService, CourseRequestValidator courseRequestValidator,
                            CourseRequestService courseRequestService) {
        this.courseService = courseService;
        this.pageBlockService = pageBlockService;
        this.courseRequestValidator = courseRequestValidator;
        this.courseRequestService = courseRequestService;
    }

    @RequestMapping(value = "/{category}/c{id:[0-9]}-{title}", method = RequestMethod.GET)
    public String get(@PathVariable("title") String title, @PathVariable("id") Long id, Model model) {


        CourseRequestFormData courseRequestFormData = new CourseRequestFormData();
        courseRequestFormData.setCourseId(id);

        setFormData(model, courseRequestFormData, id);

        return "main";
    }


    @RequestMapping(value = "/{category}/c{id:[0-9]}-{title}", method = RequestMethod.POST)
    public String submitRequest(@ModelAttribute("courseRequest") CourseRequestFormData request,
                                BindingResult result, Model model) {
        courseRequestValidator.validate(request, result);
        if (!result.hasErrors()) {
            courseRequestService.saveRequest(request);
            model.addAttribute("courseRequestSubmitSuccess", true);
        }
        setFormData(model, request, request.getCourseId());
        model.addAttribute("includeCourseJs", true);
        model.addAttribute("scrolldown", true);
        return "main";
    }

    private void setFormData(Model model, CourseRequestFormData formData, Long courseId) {
        Course course = courseService.findFull(courseId);

        if (course == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("title", course.getName() + "- ICT Cursus");
        model.addAttribute("course", course);
        model.addAttribute("mainContent", "content/course");
        model.addAttribute("courseRequest", formData);
        model.addAttribute("prefixes", Arrays.asList(Prefixes.values()));
        model.addAttribute("numberOfParticipants", NumberOfParticipants.NUMBERS);
        pageBlockService.setEmptyRightColumn(model);
        pageBlockService.setCourseCategoriesInLeftColumn(model);
    }


}
