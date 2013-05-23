package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.formdata.ArticleSuggestionFormData;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 22-5-13
 * Time: 22:48
 */
public class ArticleSuggestionToArticleSuggestionFormData implements Converter<ArticleSuggestion, ArticleSuggestionFormData> {

    @Override
    public ArticleSuggestionFormData convert(ArticleSuggestion articleSuggestion) {

        ArticleSuggestionFormData formData = new ArticleSuggestionFormData();
        if (articleSuggestion.getArticle() != null) {
            formData.setArticleId(articleSuggestion.getArticle().getId());
        }
        formData.setCreated(articleSuggestion.getCreated());
        formData.setDescription(articleSuggestion.getSuggestionText());
        formData.setEmail(articleSuggestion.getEmai());
        formData.setId(articleSuggestion.getId());
        formData.setTitle(articleSuggestion.getSuggestedTitle());
        formData.setSuggesterId(articleSuggestion.getSuggester().getId());
        formData.setApproved(articleSuggestion.isApproved());


        return formData;
    }
}
