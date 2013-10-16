package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 12:56
 */
@Component
public class CourseRequestValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(CourseRequestFormData.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CourseRequestFormData courseRequest = (CourseRequestFormData) o;

        if (!ProduxValidator.isValidEmail(courseRequest.getEmail())) {
            errors.rejectValue("email", "errors.invalid.email");
        }

        if(!ProduxValidator.isValidPhoneNumber(courseRequest.getPhone())){
            errors.rejectValue("phone", "errors.company.phone");
        }

        if (!ProduxValidator.isValidRequestMessage(courseRequest.getMessage())) {
            errors.rejectValue("message", "errors.invalid.requestmessage");
        }

        if (!ProduxValidator.isValidNumberOfParticipants(courseRequest.getNumberOfParticipants())) {
            errors.rejectValue("numberOfParticipants", "errors.invalid.numberofparticipants");
        }

        if (!ProduxValidator.isValidName(courseRequest.getRequesterName())) {
            errors.rejectValue("requesterName", "errors.invalid.name");
        }
    }
}
