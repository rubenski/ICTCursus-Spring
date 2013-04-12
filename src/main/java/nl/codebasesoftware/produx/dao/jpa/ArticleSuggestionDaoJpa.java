package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.ArticleSuggestionDao;
import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import org.springframework.stereotype.Repository;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 22:32
 */
@Repository
public class ArticleSuggestionDaoJpa extends GenericDaoJpa<ArticleSuggestion> implements ArticleSuggestionDao {

    public ArticleSuggestionDaoJpa() {
        super(ArticleSuggestion.class);
    }


}


