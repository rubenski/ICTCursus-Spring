package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.formdata.ArticlePageFormData;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 0:12
 */
public class ArticlePageToArticlePageFormData implements Converter<ArticlePage, ArticlePageFormData> {

    @Override
    public ArticlePageFormData convert(ArticlePage articlePage) {
        ArticlePageFormData formData = new ArticlePageFormData();
        formData.setArticleId(articlePage.getArticle().getId());
        formData.setId(articlePage.getId());
        formData.setMetaDescription(articlePage.getDescription());
        formData.setMetaKeywords(articlePage.getKeywords());
        formData.setPosition(articlePage.getPosition());
        formData.setText(articlePage.getBody());
        formData.setTitle(articlePage.getTitle());
        return formData;
    }
}
