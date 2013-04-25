package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.BindableCompany;
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
public class CompanyFormValidator implements Validator {

    Logger LOG = Logger.getLogger(CompanyFormValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return BindableCompany.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BindableCompany bindableCompany = (BindableCompany) target;

        String defaultRegex = "[0-9a-zA-Z+_# $&%()?'\"@=*!-/]{3,60}";
        Pattern defaultPattern = Pattern.compile(defaultRegex);

        Matcher matcher = defaultPattern.matcher(bindableCompany.getName());
        if(!matcher.matches()){
            errors.rejectValue("name", "errors.company.name");
        }

        matcher = defaultPattern.matcher(bindableCompany.getAddress());
        if(!matcher.matches()){
            errors.rejectValue("address", "errors.company.address");
        }

        if(!ProduxValidator.isValidZipCode(bindableCompany.getZipCode())){
            errors.rejectValue("zipCode", "errors.company.zipcode");
        }

        String cityRegex = "[A-Za-z '&()-]{2,25}";
        Pattern cityPattern = Pattern.compile(cityRegex);
        matcher = cityPattern.matcher(bindableCompany.getCity());
        if(!matcher.matches()){
            errors.rejectValue("city", "errors.company.city");
        }

        if(bindableCompany.getCountry().equals("-1")){
            errors.rejectValue("country", "errors.company.country");
        }

        String emailRegex = "[A-Za-z0-9._%+-]+@(?:[A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}";
        Pattern emailPattern = Pattern.compile(emailRegex);
        matcher = emailPattern.matcher(bindableCompany.getEmail());
        if(!matcher.matches()){
            errors.rejectValue("email", "errors.company.email");
        }

        String phoneRegex = "[0-9 ]{10,12}";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        matcher = phonePattern.matcher(bindableCompany.getPhone());
        if(!matcher.matches()){
            errors.rejectValue("phone", "errors.company.phone");
        }

        if(!ProduxValidator.isValidVatNumber(bindableCompany.getVatNumber())){
            errors.rejectValue("vatNumber", "errors.company.vatnumber");
        }


        if(!ProduxValidator.isValidTradeNumber(bindableCompany.getChamberOfCommerceNumber())){
            errors.rejectValue("chamberOfCommerceNumber", "errors.company.chamberofcommercenumber");
        }
    }
}
