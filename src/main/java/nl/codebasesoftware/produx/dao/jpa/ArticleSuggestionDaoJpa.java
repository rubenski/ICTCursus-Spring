package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.ArticleSuggestionDao;
import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.domain.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<ArticleSuggestion> findForUser(UserProfile user) {
        List resultList = entityManager.createQuery("from ArticleSuggestion a where a.suggester = :user").setParameter("user", user).getResultList();
        return resultList;
    }
}

