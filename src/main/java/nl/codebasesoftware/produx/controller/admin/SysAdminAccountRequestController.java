package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.domain.optionlists.ListOptions;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import nl.codebasesoftware.produx.service.AccountRequestService;
import nl.codebasesoftware.produx.service.business.BasicAccountRequestEvaluation;
import nl.codebasesoftware.produx.validator.RequestAccountFormValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 20:26
 */
@Controller
public class SysAdminAccountRequestController {

    private AccountRequestService accountRequestService;
    private MessageSource messageSource;
    private RequestAccountFormValidator validator;
    private ConversionService conversionService;
    private ListOptions options;

    @Autowired
    public SysAdminAccountRequestController(AccountRequestService accountRequestService, MessageSource messageSource,
                                            RequestAccountFormValidator validator, ConversionService conversionService,
                                            ListOptions options) {
        this.accountRequestService = accountRequestService;
        this.messageSource = messageSource;
        this.validator = validator;
        this.conversionService = conversionService;
        this.options = options;
    }

    @RequestMapping(value = "/admin/sys/accountrequests", method = RequestMethod.GET)
    public String overview(Model model, Locale locale) {
        setAccountRequestsPageData(model, locale);
        return "sysAdminMain";
    }

    @RequestMapping(value = "/admin/sys/accountrequests/{id}", method = RequestMethod.GET)
    public String showRequestForm(@PathVariable("id") Long id, Model model, Locale locale) {
        AccountRequest request = accountRequestService.find(id);
        AccountRequestFormData requestFormData = conversionService.convert(request, AccountRequestFormData.class);
        setAccountRequestData(model, locale, requestFormData, "");
        return "sysAdminMain";
    }

    @RequestMapping(value = "/admin/sys/accountrequests/acceptreject", method = RequestMethod.POST)
    public String submit(HttpServletRequest request, Model model, Locale locale) {
        setAccountRequestsPageData(model, locale);
        String id = request.getParameter("id");
        String message = request.getParameter("adminMessage");
        String accept = request.getParameter("accept");
        String reject = request.getParameter("reject");
        long requestId = Long.parseLong(id);

        if (StringUtils.isNotEmpty(accept)) {
            accountRequestService.grant(requestId, message, locale);
        } else if (StringUtils.isNotEmpty(reject)) {
            accountRequestService.reject(requestId, message, locale);
        }


        return "redirect:/admin/sys/accountrequests";
    }


    @RequestMapping(value = "/admin/sys/accountrequests/{id}", method = RequestMethod.POST)
    public String submitRequestForm(@ModelAttribute("accountRequestFormData") AccountRequestFormData formData, Model model,
                                    BindingResult result, Locale locale) {

        validator.validate(formData, result);
        String valid = "false";
        if (!result.hasErrors()) {
            accountRequestService.save(formData);
            valid = "true";
        }

        setAccountRequestData(model, locale, formData, valid);
        return "sysAdminMain";
    }

    private void setAccountRequestData(Model model, Locale locale, AccountRequestFormData requestFormData, String valid) {
        model.addAttribute("accountRequestFormData", requestFormData);
        model.addAttribute("headerText", messageSource.getMessage("sysadmin.sections.accountrequest", new Object[]{}, locale));
        model.addAttribute("mainContent", "forms/adminaccountrequest");
        model.addAttribute("countries", options.getCountries(locale));
        model.addAttribute("valid", valid);
        model.addAttribute("title", "Accountaanvragen");
    }

    private void setAccountRequestsPageData(Model model, Locale locale) {
        List<BasicAccountRequestEvaluation> unevaluatedRequests = accountRequestService.findNonEvaluated();
        List<BasicAccountRequestEvaluation> evaluatedRequests = accountRequestService.findEvaluated();
        model.addAttribute("headerText", messageSource.getMessage("sysadmin.sections.accountrequests", new Object[]{}, locale));
        model.addAttribute("numberOfUnevaluatedRequests", unevaluatedRequests.size());
        model.addAttribute("numberOfEvaluatedRequests", evaluatedRequests.size());
        model.addAttribute("unevaluatedRequests", unevaluatedRequests);
        model.addAttribute("evaluatedRequests", evaluatedRequests);
        model.addAttribute("mainContent", "content/adminaccountrequests");
        model.addAttribute("accountRequestsPage", true);

    }
}
