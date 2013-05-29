package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.ArticleDao;
import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticlePage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: rvanloen
 * Date: 7-4-13
 * Time: 21:44
 */
@Repository
public class ArticleDaoJpa extends GenericDaoJpa<Article> implements ArticleDao {

    public ArticleDaoJpa() {
        super(Article.class);
    }

    @Override
    public List<Article> findByCompany(long companyId) {
        String query = "from Article a inner join fetch a.author author where author.company.id = :companyId";
        return entityManager.createQuery(query).setParameter("companyId", companyId).getResultList();
    }

    @Override
    public List<ArticlePage> findPages(Article article) {
        return entityManager.createQuery("from ArticlePage ap where ap.article = :article order by ap.position")
                .setParameter("article", article).getResultList();
    }

    @Override
    public List<Article> findByCategory(Long categoryId) {
        return entityManager.createQuery("from Article a inner join fetch a.category c where a.category.id = :id").setParameter("id", categoryId).getResultList();
    }

    @Override
    public Article findFull(long id) {
        return getSingleResult(entityManager.createQuery("from Article a inner join fetch a.category c left join fetch a.pages ap where a.id = :id").setParameter("id", id));
    }
}
