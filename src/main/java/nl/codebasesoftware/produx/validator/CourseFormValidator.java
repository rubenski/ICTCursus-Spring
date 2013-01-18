package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.BindableCourse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: rvanloen
 * Date: 12-8-12
 * Time: 15:17
 */
@Component
public class CourseFormValidator implements Validator {

    Logger LOG = Logger.getLogger(CourseFormValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return BindableCourse.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BindableCourse bindableCourse = (BindableCourse) target;

        String regex = "[0-9]{1,5}(,[0-9]{2})?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(bindableCourse.getFormattedPrice());

        if(bindableCourse.getName().length() <= 3){
            errors.rejectValue("name", "errors.name.invalid");
        }

        if(bindableCourse.getShortDescription().length() < 10){
            errors.rejectValue("shortDescription", "errors.shortdescription.invalid");
        }

        if(bindableCourse.getLongDescription().length() < 20){
            errors.rejectValue("longDescription", "errors.longdescription.invalid");
        }

        if(bindableCourse.getCategory() == -1){
            errors.rejectValue("category", "errors.category.invalid");
        }

        if(bindableCourse.getDuration().length() < 5){
            errors.rejectValue("duration", "errors.duration.invalid");
        }

        if(bindableCourse.getRegions() == null || bindableCourse.getRegions().size() == 0){
            errors.rejectValue("regions", "errors.regions.invalid");
        }

        if(!matcher.matches()){
            errors.rejectValue("formattedPrice", "errors.formattedPrice.invalid");
        }

        if(bindableCourse.getDuration().length() > 255){
            errors.rejectValue("duration", "errors.duration.too.long");
        }

    }
}
