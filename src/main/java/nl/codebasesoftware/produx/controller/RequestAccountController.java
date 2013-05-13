package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import nl.codebasesoftware.produx.service.AccountRequestService;
import nl.codebasesoftware.produx.service.PageBlockService;
import nl.codebasesoftware.produx.util.ListOptions;
import nl.codebasesoftware.produx.validator.RequestAccountFormValidator;
import org.apache.log4j.Logger;
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
 * Date: 12-8-12
 * Time: 15:09
 */
@Controller
public class RequestAccountController {

    Logger LOG = Logger.getLogger(RequestAccountController.class);

    private RequestAccountFormValidator validator;
    private AccountRequestService accountRequestService;
    private MessageSource messageSource;
    private ListOptions options;
    private PageBlockService pageBlockService;


    @Autowired
    public RequestAccountController(RequestAccountFormValidator validator, AccountRequestService accountRequestService,
                                    MessageSource messageSource, ListOptions options, PageBlockService pageBlockService) {
        this.validator = validator;
        this.accountRequestService = accountRequestService;
        this.messageSource = messageSource;
        this.options = options;
        this.pageBlockService = pageBlockService;
    }

    @RequestMapping(value= "/requestaccount", method = RequestMethod.GET)
    public String setupForm(Model model, Locale locale) {
        setDefaultPageBlock(model);
        AccountRequestFormData accountRequestFormData = new AccountRequestFormData();
        setPageTitle(model, locale, "pagetitle.requestaccount");
        model.addAttribute("countries", options.getCountries(locale));
        model.addAttribute("accountRequestFormData", accountRequestFormData);
        model.addAttribute("mainContent", "forms/requestaccount");
        return "main";
    }


    @RequestMapping(value= "/requestaccount", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("accountRequestFormData") AccountRequestFormData accountRequestFormData, BindingResult result,
                             Model model, Locale locale) {
        validator.validate(accountRequestFormData, result);
        setDefaultPageBlock(model);
        if (!result.hasErrors()) {
            accountRequestService.save(accountRequestFormData);
            return "redirect:/requestaccount/success";
        }
        setPageTitle(model, locale, "pagetitle.requestaccount");
        model.addAttribute("valid", "false");
        model.addAttribute("countries", options.getCountries(locale));
        model.addAttribute("mainContent", "forms/requestaccount");
        return "main";
    }

    @RequestMapping(value= "/requestaccount/success", method = RequestMethod.GET)
    public String success(@ModelAttribute("accountRequestFormData") AccountRequestFormData accountRequestFormData,
                            Model model, Locale locale) {
        setPageTitle(model, locale, "pagetitle.requestaccount.success");
        model.addAttribute("mainContent", "content/requestaccountsuccess");
        setDefaultPageBlock(model);
        return "main";
    }

    private void setPageTitle(Model model, Locale locale, String titleCode){
        String title = messageSource.getMessage(titleCode, new Object[]{}, locale);
        model.addAttribute("title", title);
    }

    private void setDefaultPageBlock(Model model) {
        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setAuthentication(model);
    }


}
