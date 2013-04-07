package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.util.ImageUtil;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.validator.LogoUploadFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
    private Properties properties;
    private LogoUploadFormValidator validator;
    private CompanyService companyService;
    private MessageSource messageSource;

    @Autowired
    public LogoController(Properties properties, LogoUploadFormValidator validator, CompanyService companyService, MessageSource messageSource) {
        this.properties = properties;
        this.validator = validator;
        this.companyService = companyService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/admin/logo/upload", method = RequestMethod.POST)
    public String createOrUpdateLogo(@ModelAttribute("bindableFileUpload") BindableFileUpload bindableFileUpload, BindingResult result, Model model, Locale locale) {


        validator.validate(bindableFileUpload, result);

        String error = "";
        if (!result.hasErrors()) {

            String maxLengthString = properties.getProperty("logo.widthheight");
            int maxLength = Integer.parseInt(maxLengthString);

            byte[] bytes = null;
            CommonsMultipartFile fileData = bindableFileUpload.getFileData();
            try {
                bytes = ImageUtil.resize(fileData.getInputStream(), maxLength);
            } catch (IOException e) {
                e.printStackTrace();
            }

            companyService.updateLogo(bytes);


        } else {
            FieldError fieldError = result.getFieldError("fileData");
            String code = fieldError.getCode();
            String message = messageSource.getMessage(code, new Object[]{}, locale);
            error = message;
        }

        model.addAttribute("error", error);

        return "components/logoUploadResult";

    }

    @RequestMapping(value = "/logo/{imageName}")
    public void getLogo(@PathVariable("imageName") String imageName, HttpServletResponse response) {

        response.setContentType("image/png");

        byte[] logo = companyService.getLogo(idFromImageName(imageName));
        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            outputStream.write(logo);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long idFromImageName(String imageName) {
        return Long.parseLong(imageName.split("-")[0]);
    }

    /*
    private Logo createLogoFromForm(BindableFileUpload bindableFileUpload, Company company) {

        String uploadDir = properties.getProperty("logo.uploaddir");
        String type = bindableFileUpload.getFileData().getContentType();
        String extension = "";
        ImageType imageType = ImageType.get(type);


        if (type.equals("image/jpeg") || type.equals("image/pjpeg")) {
            extension = "jpg";
        } else if (type.equals("image/png")) {
            extension = "png";
        } else if (type.equals("image/gif")) {
            extension = "gif";
        }

        String fileName = null;
        if (company.hasLogo()) {
            fileName = company.getLogo().getFileName();
        } else {
            fileName = SecurityUtil.randomAlphaNumericString(10);
        }

        Logo logo = new Logo();
        logo.setFileExtension(extension);
        logo.setFileName(fileName);
        logo.setFileType(imageType);
        logo.setDirectoryPath(uploadDir);

        return logo;
    } */


}
