package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.ArticlePageFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 1:33
 */
@Component
public class ArticlePageFormDataValidator implements Validator {

    private static final int META_DESCRIPTION_MIN_LENGTH = 40;
    private static final int META_DESCRIPTION_MAX_LENGTH = 200;

    private static final int TITLE_MIN_LENGTH = 10;
    private static final int TITLE_MAX_LENGTH = 60;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ArticlePageFormData.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ArticlePageFormData formData = (ArticlePageFormData) o;

        if (!ProduxValidator.isValidArticleTitle(formData.getTitle())) {
            errors.rejectValue("title", "article.title.error");
        } else if (ProduxValidator.isShorterThan(formData.getTitle(), TITLE_MIN_LENGTH)) {
            errors.rejectValue("title", "title.tooshort");
        } else if (ProduxValidator.isLongerThan(formData.getTitle(), TITLE_MAX_LENGTH)) {
            errors.reject("title", "title.toolong");
        }

        if (!ProduxValidator.isValidMetaKeywords(formData.getMetaKeywords())) {
            errors.rejectValue("metaKeywords", "page.error.metakeywords");
        }

        if (!ProduxValidator.isValidMetaDescription(formData.getMetaDescription())) {
            errors.rejectValue("metaDescription", "page.error.metadescription");
        } else if (ProduxValidator.isShorterThan(formData.getMetaDescription(), META_DESCRIPTION_MIN_LENGTH)) {
            errors.rejectValue("metaDescription", "metadescription.tooshort");
        } else if (ProduxValidator.isLongerThan(formData.getMetaDescription(), META_DESCRIPTION_MAX_LENGTH)) {
            errors.reject("metaDescription", "metadescription.toolong");
        }
    }
}
