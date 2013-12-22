package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.dto.entity.AccountRequestEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.ListOptions;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import nl.codebasesoftware.produx.net.mail.AccountRequestMailer;
import nl.codebasesoftware.produx.service.AccountRequestService;
import nl.codebasesoftware.produx.service.PageBlockService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.validator.AdminAccountRequestFormValidator;
import nl.codebasesoftware.produx.validator.PublicAccountRequestFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;


/**
 * User: rvanloen
 * Date: 12-8-12
 * Time: 15:09
 */
@Controller
public class RequestAccountController {

    Logger LOG = Logger.getLogger(RequestAccountController.class);

    private PublicAccountRequestFormValidator validator;
    private AccountRequestService accountRequestService;
    private MessageSource messageSource;
    private ListOptions options;
    private PageBlockService pageBlockService;
    private AccountRequestMailer mailer;
    private UserProfileService userProfileService;


    @Autowired
    public RequestAccountController(PublicAccountRequestFormValidator validator,
                                    AccountRequestService accountRequestService,
                                    MessageSource messageSource,
                                    ListOptions options,
                                    PageBlockService pageBlockService,
                                    AccountRequestMailer mailer,
                                    UserProfileService userProfileService) {
        this.validator = validator;
        this.accountRequestService = accountRequestService;
        this.messageSource = messageSource;
        this.options = options;
        this.pageBlockService = pageBlockService;
        this.mailer = mailer;
        this.userProfileService = userProfileService;
    }

    @RequestMapping(value = "/requestaccount", method = RequestMethod.GET)
    public String setupForm(Model model, Locale locale) {
        setDefaultPageBlock(model);
        AccountRequestFormData accountRequestFormData = new AccountRequestFormData();
        setPageTitle(model, locale, "pagetitle.requestaccount");
        model.addAttribute("countries", options.getCountries(locale));
        model.addAttribute("accountRequestFormData", accountRequestFormData);
        model.addAttribute("mainContent", "forms/requestaccount");
        model.addAttribute("broadView", true);
        return "main";
    }


    @RequestMapping(value = "/requestaccount", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("accountRequestFormData") AccountRequestFormData accountRequestFormData, BindingResult result,
                             Model model, Locale locale) {
        validator.validate(accountRequestFormData, result);
        setDefaultPageBlock(model);
        if (!result.hasErrors()) {
            AccountRequestEntityDTO accountRequestEntityDTO = accountRequestService.save(accountRequestFormData);
            List<UserProfileEntityDTO> admins = userProfileService.findByRole(RoleName.SYS_ADMIN);
            mailer.sendNewAccountRequestMailToAdmin(accountRequestEntityDTO, admins, locale);
            return "redirect:/requestaccount/success";
        }
        setPageTitle(model, locale, "pagetitle.requestaccount");
        model.addAttribute("valid", "false");
        model.addAttribute("countries", options.getCountries(locale));
        model.addAttribute("mainContent", "forms/requestaccount");
        model.addAttribute("broadView", true);
        return "main";
    }

    @RequestMapping(value = "/requestaccount/success", method = RequestMethod.GET)
    public String success(@ModelAttribute("accountRequestFormData") AccountRequestFormData accountRequestFormData,
                          Model model, Locale locale) {
        setPageTitle(model, locale, "pagetitle.requestaccount.success");
        model.addAttribute("mainContent", "content/requestaccountsuccess");
        model.addAttribute("broadView", true);
        setDefaultPageBlock(model);
        return "main";
    }

    private void setPageTitle(Model model, Locale locale, String titleCode) {
        String title = messageSource.getMessage(titleCode, new Object[]{}, locale);
        model.addAttribute("title", title);
    }

    private void setDefaultPageBlock(Model model) {
        pageBlockService.setCourseCategoriesInLeftColumn(model);
        pageBlockService.setEmptyRightColumn(model);
        pageBlockService.setAuthentication(model);
    }


}
