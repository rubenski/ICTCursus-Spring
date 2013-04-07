package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableMyUserProfile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 4-4-13
 * Time: 22:18
 */
@Component
public class MyUserProfileValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfile.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BindableMyUserProfile userProfile = (BindableMyUserProfile) o;

        if (!ProduxValidator.isValidFirstName(userProfile.getFirstName())) {
            errors.rejectValue("firstName", "error.user.firstName");
        }

        if (!ProduxValidator.isValidPreposition(userProfile.getPreposition())) {
            errors.rejectValue("preposition", "error.user.preposition");
        }

        if (!ProduxValidator.isValidLastName(userProfile.getLastName())) {
            errors.rejectValue("lastName", "error.user.lastName");
        }

        if (!ProduxValidator.isValidPhoneNumber(userProfile.getPhone())) {
            errors.rejectValue("phone", "error.user.phone");
        }

    }
}
