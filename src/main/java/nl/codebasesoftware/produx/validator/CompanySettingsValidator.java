package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.CompanySettingsFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 14:47
 */
@Component
public class CompanySettingsValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(CompanySettingsFormData.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CompanySettingsFormData settings = (CompanySettingsFormData) o;

        if (!ProduxValidator.isValidEmail(settings.getCourseRequestEmailAddress())) {
            errors.rejectValue("courseRequestEmailAddress", "error.email.invalid");
        }

        if (!ProduxValidator.isValidBudgetTriggerAmount(settings.getBudgetTriggerAmount())) {
            errors.rejectValue("budgetTriggerAmount", "error.budget.triggeramount");
        }

    }
}
