package nl.codebasesoftware.produx.validator;


import nl.codebasesoftware.produx.formdata.BindableLogoUpload;
import nl.codebasesoftware.produx.util.Properties;
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
        return aClass.isAssignableFrom(BindableLogoUpload.class);
    }

    @Autowired
    public LogoUploadFormValidator(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void validate(Object target, Errors errors) {
        BindableLogoUpload bindableLogoUpload = (BindableLogoUpload) target;
        String maxKBsString = properties.getProperty("logo.maxkbs");

        int maxKBs = Integer.parseInt(maxKBsString);


        if(bindableLogoUpload.getFileData().getFileItem().getName().equals("")){
            errors.rejectValue("fileData", "errors.fileupload.nofile");
            return;
        }

        String contentType = bindableLogoUpload.getFileData().getContentType();
        if(!contentType.equals("image/jpeg") && !contentType.equals("image/png") && !contentType.equals("image/gif") && !contentType.equals("image/pjpeg")){
            errors.rejectValue("fileData", "errors.fileupload.wrongtype");
            return;
        }

        int maxBytes = maxKBs * 1024;
        if(bindableLogoUpload.getFileData().getBytes().length > maxBytes){
            errors.rejectValue("fileData", "errors.fileupload.toolarge");
            return;
        }
    }
}
