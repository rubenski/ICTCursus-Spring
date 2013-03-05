package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Logo;
import nl.codebasesoftware.produx.domain.dto.LogoDTO;
import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.util.ImageUtil;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.util.SecurityUtil;
import nl.codebasesoftware.produx.util.support.ImageType;
import nl.codebasesoftware.produx.validator.LogoUploadFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.File;
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

    /*
    @RequestMapping(value = "/manage/logo/upload", method = RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute("bindableFileUpload", new BindableFileUpload());
        return "forms/logoUpload";
    }         */

    @RequestMapping(value = "/manage/logo/upload", method = RequestMethod.POST)
    public String createOrUpdateLogo(@ModelAttribute("bindableFileUpload") BindableFileUpload bindableFileUpload, BindingResult result, Model model, Locale locale) {

        Company company = companyService.getCurrentlyLoggedInCompany();

        validator.validate(bindableFileUpload, result);

        String error = "";
        if (!result.hasErrors()) {

            String logoWidthHeight = properties.getProperty("logo.widthheight");
            int widthAndHeight = Integer.parseInt(logoWidthHeight);
            Logo logo = null;
            logo = createLogoFromForm(bindableFileUpload, company);

            boolean success = scaleAndSave(logo, bindableFileUpload, widthAndHeight);

            if (success) {
                // update image name in company entity
                companyService.setLogo(logo, company.getId());
            }

        } else {
            FieldError fieldError = result.getFieldError("fileData");
            String code = fieldError.getCode();
            String message = messageSource.getMessage(code, new Object[]{}, locale);
            error = message;
        }

        model.addAttribute("error", error);

        return "components/logoUploadResult";

    }

    private boolean scaleAndSave(Logo logo, BindableFileUpload logoUpload, int maxLength) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageUtil.scaleImage(logoUpload.getFileData().getInputStream(), maxLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ImageUtil.saveImage(bufferedImage, logo.getFullPath());
    }

    @RequestMapping(value = "/logo/{companyId}")
    public @ResponseBody LogoDTO getLogo(@PathVariable("companyId") Long companyId) {

        Company company = companyService.findById(companyId);

        if (company == null) {
            return null;
        }

        String logoDir = properties.getProperty("logo.uploaddir");
        String fileNameLengthString = properties.getProperty("logo.filename.length");

        Integer fileNameLength = null;
        try {
            fileNameLength = Integer.parseInt(fileNameLengthString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Could not get file name length. Did you properly set logo.filename.length in environment.properties?");
        }

        String fileName = company.getLogo() == null ? SecurityUtil.randomAlphaNumericString(fileNameLength) : company.getLogo().getFullFileName();

        String logoPath = String.format("%s%s", logoDir, fileName);
        String base64EncodedImage = ImageUtil.encodeBase64(new File(logoPath));

        if (base64EncodedImage == null) {
            return null;
        }

        return new LogoDTO(base64EncodedImage);
    }


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
    }


}
