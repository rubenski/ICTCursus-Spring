package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.OtherUserProfileFormData;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 5-4-13
 * Time: 23:45
 */

@Component
public class OtherUserProfileFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(OtherUserProfileFormData.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OtherUserProfileFormData formData = (OtherUserProfileFormData) o;

        if(formData.getRoles() == null || formData.getRoles().size() == 0){
            errors.rejectValue("roles", "error.user.zeroroles");
        }
    }
}
