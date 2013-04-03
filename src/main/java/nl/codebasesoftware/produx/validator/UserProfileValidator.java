package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableUserProfile;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 30-3-13
 * Time: 20:27
 */
@Component
public class UserProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfile.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BindableUserProfile userProfile = (BindableUserProfile) o;

        if(!ProduxValidator.isValidFirstName(userProfile.getFirstName())){
            errors.rejectValue("firstName", "error.user.firstName");
        }

        if(!ProduxValidator.isValidLastName(userProfile.getLastName())){
            errors.rejectValue("lastName", "error.user.lastName");
        }

        if(!ProduxValidator.isValidPassword(userProfile.getPassword1())){
            errors.rejectValue("password1", "error.user.password");
        }

        if(!userProfile.getPassword1().equals(userProfile.getPassword2())){
            errors.rejectValue("password2", "error.user.password.nomatch");
        }

        if(!ProduxValidator.isValidPhoneNumber(userProfile.getPhone())){
            errors.rejectValue("phone", "error.user.phone");
        }

        if(!ProduxValidator.isValidPreposition(userProfile.getPreposition())){
            errors.rejectValue("preposition", "error.user.preposition");
        }

    }
}
