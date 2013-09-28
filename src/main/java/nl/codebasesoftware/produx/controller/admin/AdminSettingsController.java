package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.formdata.CompanySettingsFormData;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.validator.CompanySettingsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 12:12
 */
@Controller
public class AdminSettingsController {

    private CompanyService companyService;
    private MessageSource messageSource;
    private CompanySettingsValidator validator;

    @Autowired
    public AdminSettingsController(CompanyService companyService, MessageSource messageSource, CompanySettingsValidator validator) {
        this.companyService = companyService;
        this.messageSource = messageSource;
        this.validator = validator;
    }

    @RequestMapping(value = "/admin/settings", method = RequestMethod.GET)
    public String showSettings(Model model, Locale locale) {
        CompanySettingsFormData settingsDto = companyService.getCompanySettingsForCurrentCompany();
        model.addAttribute("settings", settingsDto);
        model.addAttribute("mainContent", "forms/admincompanysettings");
        model.addAttribute("headerText", messageSource.getMessage("company.settings.header", new Object[]{}, locale));
        return "adminMain";
    }

    @RequestMapping(value = "/admin/settings", method = RequestMethod.POST)
    public String saveSettings(@ModelAttribute("settings") CompanySettingsFormData settingsDto, BindingResult result, Model model, Locale locale) {

        validator.validate(settingsDto, result);
        String valid = "false";

        if (!result.hasErrors()) {
            companyService.saveSettings(settingsDto);
            valid = "true";
        }

        model.addAttribute("settings", settingsDto);
        model.addAttribute("valid", valid);
        model.addAttribute("mainContent", "forms/admincompanysettings");
        model.addAttribute("headerText", messageSource.getMessage("company.settings.header", new Object[]{}, locale));
        return "adminMain";
    }

}
