package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.domain.UserProfile;

import java.util.List;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 22:31
 */
public interface ArticleSuggestionDao extends GenericDao<ArticleSuggestion> {


    List<ArticleSuggestion> findForUser(long userId);

    List<ArticleSuggestion> findAllDateSortedDesc();

    ArticleSuggestion findFull(Long id);
}
