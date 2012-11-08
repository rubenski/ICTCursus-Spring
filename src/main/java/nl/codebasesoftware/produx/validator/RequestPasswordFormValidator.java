package nl.codebasesoftware.produx.validator;


import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableForgotPassword;
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: rvanloen
 * Date: 7-11-12
 * Time: 15:07
 */
@Component
public class RequestPasswordFormValidator implements Validator {

    private UserProfileService userProfileService;

    @Autowired
    public RequestPasswordFormValidator(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BindableForgotPassword.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BindableForgotPassword forgotPassword = (BindableForgotPassword) target;
        Pattern p = Pattern.compile("[a-zA-Z0-9_%.-]+@[a-zA-Z0-9_%.-]+\\.[a-z]+");
        Matcher m = p.matcher(forgotPassword.getEmail());

        if(!m.matches()){
            errors.rejectValue("email", "error.email.invalid");
        }

        UserProfile profile = userProfileService.findByEmail(forgotPassword.getEmail());

        if(profile == null){
            errors.rejectValue("email", "error.email.nonexistent");
        }
    }
}
