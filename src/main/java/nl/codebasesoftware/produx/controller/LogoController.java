package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Logo;
import nl.codebasesoftware.produx.formdata.BindableLogoUpload;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.util.ImageUtil;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.util.SecurityUtil;
import nl.codebasesoftware.produx.util.support.ImageType;
import nl.codebasesoftware.produx.util.support.LogoJsonData;
import nl.codebasesoftware.produx.validator.LogoUploadFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * User: rvanloen
 * Date: 28-12-12
 * Time: 3:24
 */
@Controller
@RequestMapping("/logo")
public class LogoController {

    Logger LOG = Logger.getLogger(LogoController.class);
    private Properties properties;
    private LogoUploadFormValidator validator;
    private CompanyService companyService;

    @Autowired
    public LogoController(Properties properties, LogoUploadFormValidator validator, CompanyService companyService) {
        this.properties = properties;
        this.validator = validator;
        this.companyService = companyService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute("bindableFileUpload", new BindableLogoUpload());
        return "forms/logoUpload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String createOrUpdateLogo(BindableLogoUpload bindableFileUpload, BindingResult result, Model model) {

        Company company = companyService.getCurrentlyLoggedInCompany();

        validator.validate(bindableFileUpload, result);

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

            model.addAttribute("uploaded", 1);

        }
        model.addAttribute("bindableFileUpload", bindableFileUpload);

        return "forms/logoUpload";
    }

    private boolean scaleAndSave(Logo logo, BindableLogoUpload logoUpload, int maxLength) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageUtil.scaleImage(logoUpload.getFileData().getInputStream(), maxLength, maxLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ImageUtil.saveImage(bufferedImage, logo.getFullPath());
    }

    @RequestMapping(value = "/{companyId}")
    public @ResponseBody LogoJsonData getLogo(@PathVariable("companyId") Long companyId) {

        Company company = companyService.findById(companyId);

        if(company == null){
            return null;
        }

        String logoDir = properties.getProperty("logo.uploaddir");
        String logoPath = String.format("%s%s", logoDir, company.getLogo().getFullName());
        String base64EncodedImage = ImageUtil.encodeBase64(new File(logoPath));


        return new LogoJsonData(base64EncodedImage, company.getLogo().getDataTypeString());
    }


    private Logo createLogoFromForm(BindableLogoUpload bindableFileUpload, Company company) {

        String uploadDir = properties.getProperty("logo.uploaddir");
        String type = bindableFileUpload.getFileData().getContentType();
        String extension = "";
        ImageType imageType = ImageType.get(type);


        if (type.equals("image/jpeg")) {
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
