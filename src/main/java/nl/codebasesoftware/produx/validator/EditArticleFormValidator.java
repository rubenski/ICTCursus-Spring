package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.EditArticleFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * User: rvanloen
 * Date: 29-5-13
 * Time: 18:54
 */
@Component(value = "editArticleValidator")
public class EditArticleFormValidator extends AddArticleFormValidator {

    private final int MIN_TEXT_LENGTH = 2000;
    private final int MAX_TEXT_LENGTH = 10000;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(EditArticleFormData.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EditArticleFormData formData = (EditArticleFormData) o;

        super.validate(o, errors);

        if (!ProduxValidator.isLongerThan(formData.getText(), MIN_TEXT_LENGTH)) {
            errors.rejectValue("text", "errors.articletext.tooshort");
        }

        if (!ProduxValidator.isShorterThan(formData.getText(), MAX_TEXT_LENGTH)) {
            errors.rejectValue("text", "errors.articletext.toolong");
        }
    }


}
