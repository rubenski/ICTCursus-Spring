package nl.codebasesoftware.produx.validator;


import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 28-12-12
 * Time: 19:15
 */
@Component
public class LogoUploadFormValidator implements Validator {


    private Properties properties;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(BindableFileUpload.class);
    }

    @Autowired
    public LogoUploadFormValidator(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void validate(Object target, Errors errors) {
        BindableFileUpload fileUpload = (BindableFileUpload) target;
        String maxKBsString = properties.getProperty("img.upload.maxkbs");

        int maxKBs = Integer.parseInt(maxKBsString);

        if (fileUpload.getFileData().getFileItem().getName().equals("")) {
            errors.rejectValue("fileData", "errors.fileupload.nofile");
            return;
        }

        String contentType = fileUpload.getFileData().getContentType();
        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png") && !contentType.equals("image/gif") && !contentType.equals("image/pjpeg")) {
            errors.rejectValue("fileData", "errors.fileupload.wrongtype");
            return;
        }

        int maxBytes = maxKBs * 1024;
        if (fileUpload.getFileData().getBytes().length > maxBytes) {
            errors.rejectValue("fileData", "errors.fileupload.toolarge");
            return;
        }
    }
}
