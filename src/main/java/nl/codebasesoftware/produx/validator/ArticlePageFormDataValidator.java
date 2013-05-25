package nl.codebasesoftware.produx.validator;

import nl.codebasesoftware.produx.formdata.ArticlePageFormData;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 1:33
 */
@Component
public class ArticlePageFormDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ArticlePageFormData.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ArticlePageFormData formData = (ArticlePageFormData) o;

        if(!ProduxValidator.isValidArticleTitle(formData.getTitle())){
            errors.rejectValue("title", "article.title.error");
        }

        if(!ProduxValidator.isValidMetaKeywords(formData.getMetaKeywords())){
            errors.rejectValue("metaKeywords", "page.error.metakeywords");
        }

        if(!ProduxValidator.isValidMetaDescription(formData.getMetaDescription())){
            errors.rejectValue("metaDescription", "page.error.metadescription");
        }
    }
}
