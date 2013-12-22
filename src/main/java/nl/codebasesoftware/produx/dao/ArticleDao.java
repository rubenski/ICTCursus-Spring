package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;

import java.util.List;

/**
 * User: rvanloen
 * Date: 7-4-13
 * Time: 21:39
 */
public interface ArticleDao extends GenericDao<Article> {
    List<Article> findByCompany(long companyId);

    List<ArticlePage> findPages(ArticleEntityDTO article);

    List<Article> findByCategory(Long catgeoryId);

    Article findFull(long id);
}
