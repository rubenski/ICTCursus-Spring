package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.ListOptions;
import nl.codebasesoftware.produx.formdata.CompanyFormData;
import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.validator.CompanyFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;


/**
 * User: rvanloen
 * Date: 27-12-12
 * Time: 22:07
 */
@Controller
public class AdminCompanyController {

    private CompanyService companyService;
    private MessageSource messageSource;
    private CompanyFormValidator validator;
    private ListOptions listOptions;


    @Autowired
    public AdminCompanyController(CompanyService companyService, MessageSource messageSource, CompanyFormValidator validator,
                                  ListOptions listOptions) {

        this.companyService = companyService;
        this.messageSource = messageSource;
        this.validator = validator;
        this.listOptions = listOptions;

    }

    @RequestMapping(value = "/admin/company", method = RequestMethod.GET)
    public String getCompanyForm(Model model, Locale locale) {

        CompanyEntityDTO company = companyService.getCurrentlyLoggedInCompany();

        model.addAttribute("headerText", messageSource.getMessage("admin.sections.companyprofile", new Object[]{}, locale));
        model.addAttribute("bindableFileUpload", new BindableFileUpload());
        model.addAttribute("countries", listOptions.getCountries(locale));
        model.addAttribute("companyFormData", company.toCompanyFormData());
        model.addAttribute("mainContent", "forms/companyform");
        model.addAttribute("companyform", true);

        return "adminMain";
    }

    @RequestMapping(value = "/admin/company", method = RequestMethod.POST)
    public String updateCompany(@ModelAttribute("companyFormData") CompanyFormData companyFormData, BindingResult result, Model model, Locale locale) {

        validator.validate(companyFormData, result);
        String valid = "false";

        if (!result.hasErrors()) {
            companyService.update(companyFormData);
            valid = "true";
        }

        model.addAttribute("headerText", messageSource.getMessage("admin.sections.companyinfo", new Object[]{}, locale));
        model.addAttribute("bindableFileUpload", new BindableFileUpload());
        model.addAttribute("countries", listOptions.getCountries(locale));
        model.addAttribute("companyFormData", companyFormData);
        model.addAttribute("valid", valid);
        model.addAttribute("mainContent", "forms/companyform");
        model.addAttribute("companyform", true);

        return "adminMain";
    }

    @RequestMapping(value = "/company/getcurrent", method = RequestMethod.GET)
    public
    @ResponseBody
    CompanyFormData getCompany() {
        CompanyEntityDTO company = companyService.getCurrentlyLoggedInCompany();
        return company.toCompanyFormData();
    }


}
