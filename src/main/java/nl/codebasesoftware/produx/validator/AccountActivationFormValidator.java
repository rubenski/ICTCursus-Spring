package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.AccountActivationFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 8:44
 */
@Component
public class AccountActivationFormValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(AccountActivationFormValidator.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountActivationFormData formData = (AccountActivationFormData) o;

        if(!ProduxValidator.isValidFirstName(formData.getFirstName())){
            errors.rejectValue("firstName", "error.user.firstName");
        }

        if(!ProduxValidator.isValidLastName(formData.getLastName())){
            errors.rejectValue("lastName", "error.user.lastName");
        }

        if(!ProduxValidator.isValidPreposition(formData.getLastName())){
            errors.rejectValue("lastName", "error.user.lastName");
        }

        if(!ProduxValidator.isValidPhoneNumber(formData.getPhone())){
            errors.rejectValue("phone", "error.user.phone");
        }

        if(!ProduxValidator.isValidPassword(formData.getPassword1())){
            errors.rejectValue("password1", "error.user.password");
        }

        if(!formData.getPassword1().equals(formData.getPassword2())){
            errors.rejectValue("password2", "error.user.password.nomatch");
        }
    }
}
