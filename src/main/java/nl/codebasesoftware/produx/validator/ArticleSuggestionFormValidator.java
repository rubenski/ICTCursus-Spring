package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.ArticleSuggestionFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 22:21
 */
@Component
public class ArticleSuggestionFormValidator implements Validator {

    private int MINIMAL_SUGGESTION_LENGTH = 30;
    private int MAXIMAL_SUGGESTION_LENGTH = 1000;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ArticleSuggestionFormValidator.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ArticleSuggestionFormData formData = (ArticleSuggestionFormData) o;

        if (!ProduxValidator.isValidArticleTitle(formData.getTitle())) {
            errors.rejectValue("title", "article.error.wrongtitle");
        }

        if (!ProduxValidator.isValidNormalText(formData.getDescription(), false)) {
            errors.rejectValue("description", "articlesuggestion.invalidtext");
        }

        if (ProduxValidator.isLongerThan(formData.getDescription(), MAXIMAL_SUGGESTION_LENGTH)) {
            errors.rejectValue("description", "error.texttoolong", new Object[]{MAXIMAL_SUGGESTION_LENGTH}, "");
        }

        if (ProduxValidator.isShorterThan(formData.getDescription(), MINIMAL_SUGGESTION_LENGTH)) {
            errors.rejectValue("description", "error.texttooshort", new Object[]{MINIMAL_SUGGESTION_LENGTH}, "");
        }

        if (!ProduxValidator.isValidEmail(formData.getEmail())) {
            errors.rejectValue("email", "error.email.invalid");
        }
    }
}
