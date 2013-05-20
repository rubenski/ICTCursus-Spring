package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.optionlists.NumberOfParticipants;
import nl.codebasesoftware.produx.domain.optionlists.Prefixes;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.CourseRequestService;
import nl.codebasesoftware.produx.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 19-5-13
 * Time: 1:19
 */
@Controller
public class AdminCourseRequestController {


    private CourseRequestService requestService;
    private CompanyService companyService;
    private MessageSource messageSource;
    private ConversionService conversionService;
    private CourseService courseService;

    @Autowired
    public AdminCourseRequestController(CourseRequestService requestService, CompanyService companyService,
                                        MessageSource messageSource, ConversionService conversionService, CourseService courseService) {
        this.requestService = requestService;
        this.companyService = companyService;
        this.messageSource = messageSource;
        this.conversionService = conversionService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/admin/courserequests", method = RequestMethod.GET)
    public String forCompany(Model model, Locale locale){

        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        List<CourseRequest> courseRequests = requestService.findForCompany(currentlyLoggedInCompany.getId());

        model.addAttribute("mainContent", "content/adminCourseRequests");
        model.addAttribute("courseRequests", courseRequests);
        model.addAttribute("numberOfRequests", courseRequests.size());
        model.addAttribute("headerText", messageSource.getMessage("admin.your.courserequests", new Object[]{}, locale));


        return "adminMain";
    }

    @RequestMapping(value = "/admin/courserequests/{id}", method = RequestMethod.GET)
    public String showRequest(@PathVariable("id") Long id, Model model, Locale locale){

        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        CourseRequest request = requestService.findById(id);

        boolean isOwner = requestService.belongsTo(currentlyLoggedInCompany, request);

        if(!isOwner){
            throw new ResourceNotFoundException();
        }

        CourseRequestFormData formData = conversionService.convert(request, CourseRequestFormData.class);
        Course course = courseService.findById(formData.getCourseId());

        model.addAttribute("mainContent", "forms/adminCourseRequest");
        model.addAttribute("prefixes", Arrays.asList(Prefixes.values()));
        model.addAttribute("numberOfParticipants", NumberOfParticipants.NUMBERS);
        model.addAttribute("courseRequestFormData", formData);
        model.addAttribute("course", course);
        model.addAttribute("headerText", messageSource.getMessage("admin.your.courserequest", new Object[]{request.getId()}, locale));


        return "adminMain";
    }
}
