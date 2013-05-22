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
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private UserProfileService userProfileService;

    @Autowired
    public AdminCourseRequestController(CourseRequestService requestService, CompanyService companyService,
                                        MessageSource messageSource, ConversionService conversionService, CourseService courseService,
                                        UserProfileService userProfileService) {
        this.requestService = requestService;
        this.companyService = companyService;
        this.messageSource = messageSource;
        this.conversionService = conversionService;
        this.courseService = courseService;
        this.userProfileService = userProfileService;
    }

    @RequestMapping(value = "/admin/courserequests", method = RequestMethod.GET)
    public String forCompany(Model model, Locale locale) {

        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        List<CourseRequest> courseRequests = requestService.findForCompany(currentlyLoggedInCompany.getId());

        model.addAttribute("mainContent", "content/adminCourseRequests");
        model.addAttribute("courseRequests", courseRequests);
        model.addAttribute("numberOfRequests", courseRequests.size());
        model.addAttribute("headerText", messageSource.getMessage("admin.your.courserequests", new Object[]{}, locale));

        return "adminMain";
    }

    @RequestMapping(value = "/admin/sys/courserequests", method = RequestMethod.GET)
    public String forCompanyViewedBySysAdmin(Model model, Locale locale) {
        List<CourseRequest> courseRequests = requestService.findAllDateSortedDesc();

        model.addAttribute("mainContent", "content/sysAdminCourseRequests");
        model.addAttribute("courseRequests", courseRequests);
        model.addAttribute("numberOfRequests", courseRequests.size());
        model.addAttribute("headerText", messageSource.getMessage("admin.sections.requests", new Object[]{}, locale));

        return "sysAdminMain";
    }

    @RequestMapping(value = "/admin/sys/courserequests/{id}", method = RequestMethod.POST)
    public String saveRequest(@ModelAttribute("courseRequestFormData") CourseRequestFormData formData, Model model, Locale locale) {
        requestService.setInvalid(formData.getId(), formData.isInvalid());
        CourseRequest request = requestService.findById(formData.getId());
        fillModel(model, locale, request, true);
        model.addAttribute("valid", "true");
        return "sysAdminMain";
    }

    @RequestMapping(value = "/admin/courserequests/{id}", method = RequestMethod.GET)
    public String showRequest(@PathVariable("id") Long id, Model model, Locale locale) {
        CourseRequest request = requestService.findById(id);
        currentCompanyIsOwner(request);
        fillModel(model, locale, request, true);
        return "adminMain";
    }


    @RequestMapping(value = "/admin/sys/courserequests/{id}", method = RequestMethod.GET)
    public String showRequestSysAdmin(@PathVariable("id") Long id, Model model, Locale locale) {
        CourseRequest request = requestService.findById(id);
        fillModel(model, locale, request, true);
        return "sysAdminMain";
    }

    private void fillModel(Model model, Locale locale, CourseRequest request, boolean isSysAdmin) {

        if (request == null) {
            throw new ResourceNotFoundException();
        }
        CourseRequestFormData formData = conversionService.convert(request, CourseRequestFormData.class);
        Course course = courseService.findById(formData.getCourseId());

        model.addAttribute("mainContent", "forms/adminCourseRequest");
        model.addAttribute("prefixes", Arrays.asList(Prefixes.values()));
        model.addAttribute("numberOfParticipants", NumberOfParticipants.NUMBERS);
        model.addAttribute("courseRequestFormData", formData);
        model.addAttribute("course", course);
        model.addAttribute("headerText", messageSource.getMessage("admin.your.courserequest", new Object[]{formData.getId()}, locale));
        model.addAttribute("isSysAdmin", isSysAdmin);

    }

    private void currentCompanyIsOwner(CourseRequest request) {
        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        boolean isOwner = requestService.belongsTo(currentlyLoggedInCompany, request);

        if (!isOwner) {
            throw new ResourceNotFoundException();
        }
    }


}
