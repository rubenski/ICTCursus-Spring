package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import org.springframework.validation.Errors;

/**
 * User: rvanloen
 * Date: 20-11-13
 * Time: 19:36
 */
public class AccountRequestPublicFormValidator {

    public static void validate(AccountRequestFormData request, Errors errors){

        if (!ProduxValidator.isValidAddrress(request.getCompanyAddress())) {
            errors.rejectValue("companyAddress", "errors.company.address");
        }

        if (!ProduxValidator.isValidCity(request.getCompanyCity())) {
            errors.rejectValue("companyCity", "errors.company.city");
        }

        if (request.getCountry().equals("-1")) {
            errors.rejectValue("country", "errors.company.country");
        }

        if (!ProduxValidator.isValidEmail(request.getCompanyEmail())) {
            errors.rejectValue("companyEmail", "error.email.invalid");
        }

        if (!ProduxValidator.isValidPhoneNumber(request.getCompanyPhone())) {
            errors.rejectValue("companyPhone", "error.user.phone");
        }

        if (!ProduxValidator.isValidFirstName(request.getFirstName())) {
            errors.rejectValue("firstName", "error.user.firstName");
        }

        if (!ProduxValidator.isValidFirstName(request.getFirstName())) {
            errors.rejectValue("lastName", "error.user.lastName");
        }

        if (!ProduxValidator.isValidPreposition(request.getPreposition())) {
            errors.rejectValue("preposition", "error.user.preposition");
        }

        if (!ProduxValidator.isValidEmail(request.getEmail())) {
            errors.rejectValue("email", "error.email.invalid");
        }

        if (!ProduxValidator.isValidPhoneNumber(request.getPhone())) {
            errors.rejectValue("phone", "error.user.phone");
        }

        if (!ProduxValidator.isValidCompanyName(request.getCompanyName())) {
            errors.rejectValue("companyName", "errors.company.name");
        }

        if (!ProduxValidator.isValidTradeNumber(request.getTradeNumber())) {
            errors.rejectValue("tradeNumber", "errors.company.chamberofcommercenumber");
        }

        if (!ProduxValidator.isValidZipCode(request.getCompanyZipCode())) {
            errors.rejectValue("companyZipCode", "errors.company.zipcode");
        }

        if (!ProduxValidator.isValidVatNumber(request.getVatNumber())) {
            errors.rejectValue("vatNumber", "errors.company.vatnumber");
        }
    }
}
