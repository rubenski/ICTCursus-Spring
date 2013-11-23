package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.PasswordFormData;
import nl.codebasesoftware.produx.service.SecurityService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 21-11-13
 * Time: 13:18
 */
@Component
public class NewPasswordFormValidator implements Validator {

    private UserProfileService userProfileService;
    private SecurityService securityService;

    @Autowired
    public NewPasswordFormValidator(UserProfileService userProfileService, SecurityService securityService) {
        this.userProfileService = userProfileService;
        this.securityService = securityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPasswordFormValidator.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordFormData data = (PasswordFormData) target;

        if (StringUtil.isNullOrEmpty(data.getExistingPassword())) {
            errors.rejectValue("existingPassword", "errors.empty.existing.password");
        } else {
            UserProfileEntityDTO user = userProfileService.getCurrentlyLoggedInUser();
            if (!securityService.getHash(data.getExistingPassword()).equals(user.getPasswordHash())) {
                errors.rejectValue("existingPassword", "errors.incorrect.existing.password");
            }
        }

        if(!ProduxValidator.isValidPassword(data.getNewPassword1())){
            errors.rejectValue("newPassword1", "error.user.password");
        }else{
            if(!data.getNewPassword1().equals(data.getNewPassword2())){
                errors.rejectValue("newPassword2", "error.user.password.nomatch");
            }
        }
    }
}
