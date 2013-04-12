package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.formdata.ArticleSuggestionFormData;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 22:28
 */
public interface ArticleSuggestionService {

    ArticleSuggestion findById(long id);
    ArticleSuggestion save(ArticleSuggestionFormData formData);

}
