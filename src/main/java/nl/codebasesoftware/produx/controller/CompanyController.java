package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.support.Country;
import nl.codebasesoftware.produx.formdata.BindableCompany;
import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.validator.CompanyFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * User: rvanloen
 * Date: 27-12-12
 * Time: 22:07
 */
@Controller
public class CompanyController {

    private CompanyService companyService;
    private MessageSource messageSource;
    private CompanyFormValidator validator;

    private Logger LOG = Logger.getLogger(CompanyController.class);

    @Autowired
    public CompanyController(CompanyService companyService, MessageSource messageSource, CompanyFormValidator validator) {
        this.companyService = companyService;
        this.messageSource = messageSource;
        this.validator = validator;
    }

    @RequestMapping(value= "/admin/company", method = RequestMethod.GET)
    public String getCompanyForm(Model model, Locale locale) {

        Company company = companyService.getCurrentlyLoggedInCompany();


        model.addAttribute("bindableFileUpload", new BindableFileUpload());
        model.addAttribute("countries", getCountries(locale));
        model.addAttribute("bindableCompany", company.toBindableCompany());
        model.addAttribute("mainContent", "forms/companyform");

        return "adminMain";
    }

    @RequestMapping(value= "/admin/company", method = RequestMethod.POST)
    public String update(@ModelAttribute("bindableCompany") BindableCompany bindableCompany, BindingResult result, Model model, Locale locale) {

        validator.validate(bindableCompany, result);
        String courseValid = "false";

        if (!result.hasErrors()) {
            companyService.update(bindableCompany);
            courseValid = "true";
        }

        model.addAttribute("countries", getCountries(locale));
        model.addAttribute("bindableCompany", bindableCompany);
        model.addAttribute("courseValid", courseValid);
        model.addAttribute("mainContent", "forms/companyform");

        return "adminMain";
    }

    @RequestMapping(value="/company/getcurrent", method = RequestMethod.GET)
    public @ResponseBody BindableCompany getCompany(){
        Company company = companyService.getCurrentlyLoggedInCompany();
        return company.toBindableCompany();
    }

    private List<Country> getCountries(Locale locale) {
        List<Country> countries = new ArrayList<Country>();
        countries.add(new Country("NL", messageSource.getMessage("countries.netherlands", new Object[]{}, locale)));
        countries.add(new Country("BE", messageSource.getMessage("countries.belgium", new Object[]{}, locale)));
        return countries;
    }


}
