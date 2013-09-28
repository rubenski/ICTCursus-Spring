package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.AddArticleFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 8-4-13
 * Time: 9:59
 */
@Component(value = "addArticleValidator")
public class AddArticleFormValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(AddArticleFormValidator.class);
    }


    public void validate(Object o, Errors errors) {
        AddArticleFormData formData = (AddArticleFormData) o;

        if (!ProduxValidator.isValidArticleTitle(formData.getTitle())) {
            errors.rejectValue("title", "article.error.wrongtitle");
        }

        if (!ProduxValidator.isArticleTeaserLongEnough(formData.getTeaser())) {
            errors.rejectValue("teaser", "article.error.teasertooshort");
        } else {
            if (!ProduxValidator.isArticleTeaserValid(formData.getTeaser())) {
                errors.rejectValue("teaser", "article.error.teaserinvalid");
            }
        }

        if (formData.getCategory() == -1) {
            errors.rejectValue("category", "errors.category.invalid");
        }
    }
}
