package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.formdata.BindableFileUpload;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 28-12-12
 * Time: 3:24
 */
@Controller
public class LogoController {

    Logger LOG = Logger.getLogger(LogoController.class);
    private LogoUploadFormValidator validator;
    private CompanyService companyService;
    private MessageSource messageSource;

    @Autowired
    public LogoController(LogoUploadFormValidator validator, CompanyService companyService, MessageSource messageSource) {
        this.validator = validator;
        this.companyService = companyService;
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

    @RequestMapping(value = "/logo/{size}/{imageName}")
    public void getLogo(@PathVariable("imageName") String imageName, @PathVariable("size") String size, HttpServletResponse response) {

        response.setContentType("image/png");

        Long companyId = idFromImageName(imageName);
        Company company = companyService.findById(companyId);

        byte[] logo = null;

        if (company != null && size.equals("small")) {
            logo = company.getSmallLogo();
        } else if (company != null && size.equals("normal")) {
            logo = company.getNormalLogo();
        } else {
            throw new ResourceNotFoundException();
        }

        ServletOutputStream outputStream = null;

        if (logo != null) {
            try {
                outputStream = response.getOutputStream();
                outputStream.write(logo);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value = "/admin/logo/remove/{companyId}", method = RequestMethod.POST)
    @ResponseBody
    public void removeLogo(@PathVariable(value = "companyId") long companyId) {
        companyService.removeLogo(companyId);
    }

    @RequestMapping(value = "/admin/logo/has/{companyId}", method = RequestMethod.POST)
    @ResponseBody
    public boolean hasLogo(@PathVariable(value = "companyId") long companyId) {
        Company company = companyService.findById(companyId);
        return company.hasLogo();
    }

    private Long idFromImageName(String imageName) {
        return Long.parseLong(imageName.split("-")[0]);
    }


}
