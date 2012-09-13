package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 12-8-12
 * Time: 15:17
 */
@Component
public class CreateUserProfileFormValidator implements Validator {

    Logger LOG = Logger.getLogger(CreateUserProfileFormValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfile.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserProfile profile = (UserProfile) target;
        String firstName = profile.getFirstName();
        String lastName = profile.getLastName();
        String email = profile.getEmail();
        String password = profile.getPassword();
        String phone = profile.getPhone();
        Company companyId = profile.getCompany();
        Long id = profile.getId();

        if(firstName.length() <= 1){
            errors.rejectValue("firstName", "errors.firstName.required");
        }

    }
}
