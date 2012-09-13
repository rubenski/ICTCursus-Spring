package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.domain.UserProfile;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 15-8-12
 * Time: 11:30
 */
@Component
public class LoginFormValidator implements Validator {
    Logger LOG = Logger.getLogger(LoginFormValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfile.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserProfile profile = (UserProfile) target;
        String email = profile.getEmail();
        String password = profile.getPassword();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, email, "errors.email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, password, "errors.email.required");
    }
}
