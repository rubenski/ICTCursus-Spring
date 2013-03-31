package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 17:44
 */
@Component
public class UserInvitationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(BindableUserInvitation.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BindableUserInvitation userInvitation = (BindableUserInvitation) o;


        if(!ProduxValidator.isValidFirstName(userInvitation.getFirstName())){
            errors.rejectValue("firstName", "error.user.firstName");
        }

        if(!ProduxValidator.isValidLastName(userInvitation.getLastName())){
            errors.rejectValue("lastName", "error.user.lastName");
        }

        if(!ProduxValidator.isValidEmail(userInvitation.getEmail())){
            errors.rejectValue("email", "error.email.invalid");
        }

        if(userInvitation.getRoles() == null || userInvitation.getRoles().size() == 0){
            errors.rejectValue("roles", "error.userinvitation.zeroroles");
        }
    }
}
