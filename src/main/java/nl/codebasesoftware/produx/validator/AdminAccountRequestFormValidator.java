package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import nl.codebasesoftware.produx.service.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 12-8-12
 * Time: 15:17
 */
@Component
public class AdminAccountRequestFormValidator implements Validator {

    Logger LOG = Logger.getLogger(AdminAccountRequestFormValidator.class);
    private CompanyService companyService;

    @Autowired
    public AdminAccountRequestFormValidator(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AccountRequestFormData.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountRequestFormData request = (AccountRequestFormData) target;

        AccountRequestPublicFormValidator.validate(request, errors);

        CompanyEntityDTO companyEntityDTO = companyService.findByPrefix(request.getCompanyPrefix());

        if(companyEntityDTO != null){
            errors.rejectValue("companyPrefix", "company.prefix.exists");
        }

        if (!ProduxValidator.isValidCompanyPrefix(request.getCompanyPrefix())) {
            errors.rejectValue("companyPrefix", "company.prefix.invalid");
        }



    }
}
