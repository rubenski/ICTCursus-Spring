package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.ArticleSuggestionFormData;

import java.util.List;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 22:28
 */
public interface ArticleSuggestionService {

    ArticleSuggestion findById(long id);
    ArticleSuggestion save(ArticleSuggestionFormData formData);
    List<ArticleSuggestion> findForUser(UserProfile user);
    List<ArticleSuggestion> findAllDateSortedDesc();
    ArticleSuggestion findFull(Long id);
    void setApproval(Long id, boolean approved);
}
