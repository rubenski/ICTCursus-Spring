package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxSecurityException;
import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.service.ArticleService;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.validator.LogoUploadFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * User: rvanloen
 * Date: 28-12-12
 * Time: 3:24
 */
@Controller
public class AdminUploadController {

    Logger LOG = Logger.getLogger(AdminUploadController.class);
    private LogoUploadFormValidator validator;
    private CompanyService companyService;
    private ArticleService articleService;
    private MessageSource messageSource;

    @Autowired
    public AdminUploadController(LogoUploadFormValidator validator, CompanyService companyService, ArticleService articleService, MessageSource messageSource) {
        this.validator = validator;
        this.companyService = companyService;
        this.articleService = articleService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/admin/logo/upload", method = RequestMethod.POST)
    public String createOrUpdateLogo(@ModelAttribute("bindableFileUpload") BindableFileUpload bindableFileUpload, BindingResult result, Model model, Locale locale) {

        validator.validate(bindableFileUpload, result);

        String error = "";
        if (!result.hasErrors()) {
            companyService.updateLogo(bindableFileUpload);
        } else {
            FieldError fieldError = result.getFieldError("fileData");
            String code = fieldError.getCode();
            String message = messageSource.getMessage(code, new Object[]{}, locale);
            error = message;
        }

        model.addAttribute("error", error);

        return "components/logoUploadResult";

    }

    @RequestMapping(value = "/admin/article/setimage/{id}", method = RequestMethod.POST)
    public String setImageForArticle(@ModelAttribute("bindableFileUpload") BindableFileUpload bindableFileUpload, @PathVariable("id") long id,
                                   BindingResult result, Model model, Locale locale) throws ProduxSecurityException {

        validator.validate(bindableFileUpload, result);
        CompanyEntityDTO company = companyService.getCurrentlyLoggedInCompany();

        String error = "";
        if (!result.hasErrors()) {
            articleService.updateLogo(bindableFileUpload, id, company);
        } else {
            FieldError fieldError = result.getFieldError("fileData");
            String code = fieldError.getCode();
            String message = messageSource.getMessage(code, new Object[]{}, locale);
            error = message;
        }

        model.addAttribute("error", error);

        return "components/logoUploadResult";
    }


    @RequestMapping(value = "/admin/logo/has/{companyId}", method = RequestMethod.POST)
    @ResponseBody
    public boolean hasLogo(@PathVariable(value = "companyId") long companyId) {
        CompanyEntityDTO company = companyService.findById(companyId);
        return company.hasLogo();
    }




}
