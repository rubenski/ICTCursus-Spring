package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.AccountRequestDecision;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 23:00
 */
@Component
public class AccountRequestDecisionValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(AccountRequestDecision.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountRequestDecision decision = (AccountRequestDecision) o;
        if(decision.getDecision() == -1){
            errors.rejectValue("decision", "accountrequest.decision.error");
        }
    }
}
