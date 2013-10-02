package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.NumberOfParticipants;
import nl.codebasesoftware.produx.domain.optionlists.Prefixes;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
import nl.codebasesoftware.produx.search.criteria.filter.Filter;
import nl.codebasesoftware.produx.search.criteria.filter.NormalFilter;
import nl.codebasesoftware.produx.search.result.SearchResult;
import nl.codebasesoftware.produx.service.CourseRequestService;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.PageBlockService;
import nl.codebasesoftware.produx.service.SearchService;
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
    private CourseRequestValidator courseRequestValidator;
    private CourseRequestService courseRequestService;
    private SearchService searchService;

    @Autowired
    public CourseController(CourseService courseService, CourseRequestValidator courseRequestValidator,
                            CourseRequestService courseRequestService, SearchService searchService) {
        this.courseService = courseService;
        this.courseRequestValidator = courseRequestValidator;
        this.courseRequestService = courseRequestService;
        this.searchService = searchService;
    }

    @RequestMapping(value = "/{category}/{id:[\\d]+}/{title}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id,
                      Model model) throws ProduxServiceException {
        CourseRequestFormData courseRequestFormData = new CourseRequestFormData();
        courseRequestFormData.setCourseId(id);
        setData(model, courseRequestFormData, id);
        return "main";
    }


    @RequestMapping(value = "/{category}/c{id:[0-9]}-{title}", method = RequestMethod.POST)
    public String submitRequest(@ModelAttribute("courseRequest") CourseRequestFormData request,
                                BindingResult result, Model model) throws ProduxServiceException {
        courseRequestValidator.validate(request, result);
        if (!result.hasErrors()) {
            courseRequestService.saveRequest(request);
            model.addAttribute("courseRequestSubmitSuccess", true);
        }
        setData(model, request, request.getCourseId());
        model.addAttribute("includeCourseJs", true);
        model.addAttribute("scrolldown", true);
        return "main";
    }

    private void setData(Model model, CourseRequestFormData formData, Long courseId) throws ProduxServiceException {
        CourseEntityDTO course = courseService.findFull(courseId);

        if (course == null) {
            throw new ResourceNotFoundException();
        }

        SearchCriteria.Builder criteriaBuilder = new SearchCriteria.Builder();
        Filter companyFilter = new NormalFilter("company_id", course.getCompany().getId());
        Filter excludeCurrentCourseFilter = new NormalFilter("course_id", course.getId());
        excludeCurrentCourseFilter.setNegative(true);
        SearchCriteria criteria = criteriaBuilder.addFilter(companyFilter)
                                                    .addFilter(excludeCurrentCourseFilter)
                                                    .setStart(0)
                                                    .setRows(10)
                                                    .build();
        SearchResult otherCourses = searchService.findCourses(criteria);

        model.addAttribute("rightColumn", "content/coursedetails");
        model.addAttribute("title", course.getName() + "- ICT Cursus");
        model.addAttribute("course", course);
        model.addAttribute("mainContent", "content/course");
        model.addAttribute("courseRequest", formData);
        model.addAttribute("prefixes", Arrays.asList(Prefixes.values()));
        model.addAttribute("numberOfParticipants", NumberOfParticipants.NUMBERS);
        model.addAttribute("isCourse", true);
        model.addAttribute("otherCourses", otherCourses);


    }


}
