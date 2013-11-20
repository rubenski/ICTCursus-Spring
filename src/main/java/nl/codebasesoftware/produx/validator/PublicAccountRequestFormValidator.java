package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 20-11-13
 * Time: 20:07
 */
@Component
public class PublicAccountRequestFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AccountRequestFormData.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountRequestFormData request = (AccountRequestFormData) target;
        AccountRequestPublicFormValidator.validate(request, errors);
    }
}
