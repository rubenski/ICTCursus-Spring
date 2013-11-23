package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleSuggestionEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.ArticleSuggestionFormData;

import java.util.List;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 22:28
 */
public interface ArticleSuggestionService {

    ArticleSuggestionEntityDTO findById(long id);

    ArticleSuggestion insert(ArticleSuggestionFormData formData);

    List<ArticleSuggestionEntityDTO> findForUser(UserProfileEntityDTO user);

    List<ArticleSuggestionEntityDTO> findAllDateSortedDesc();

    ArticleSuggestion findFull(Long id);

    void setApproval(Long id, boolean approved);
}
