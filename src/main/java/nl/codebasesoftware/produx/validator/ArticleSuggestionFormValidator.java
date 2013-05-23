package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.ArticleSuggestionFormData;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 22:21
 */
@Component
public class ArticleSuggestionFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ArticleSuggestionFormValidator.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ArticleSuggestionFormData formData = (ArticleSuggestionFormData) o;

        if(!ProduxValidator.isValidArticleTitle(formData.getTitle())){
            errors.rejectValue("title", "article.error.wrongtitle");
        }

        if(!ProduxValidator.isValidSuggestionText(formData.getDescription())){
            errors.rejectValue("description", "articlesuggestion.invalidtext");
        }

        if(!ProduxValidator.isValidEmail(formData.getEmail())){
            errors.rejectValue("email", "error.email.invalid");
        }

    }
}
