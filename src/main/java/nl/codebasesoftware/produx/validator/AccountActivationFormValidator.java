package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.BindableUsenamePassword;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 8:44
 */
public class AccountActivationFormValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(Object o, Errors errors) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
