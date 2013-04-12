package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.ArticlePageDao;
import nl.codebasesoftware.produx.domain.ArticlePage;
import org.springframework.stereotype.Repository;

/**
 * User: rvanloen
 * Date: 11-4-13
 * Time: 22:50
 */
@Repository
public class ArticlePageDaoJpa extends GenericDaoJpa<ArticlePage> implements ArticlePageDao {

    public ArticlePageDaoJpa() {
        super(ArticlePage.class);
    }


}


