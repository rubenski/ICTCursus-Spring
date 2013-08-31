package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


/**
 * User: rvanloen
 * Date: 8-11-12
 * Time: 2:31
 */
@Repository
public class CompanyDaoJpa extends GenericDaoJpa<Company> implements CompanyDao {

    public CompanyDaoJpa() {
        super(Company.class);
    }


    @Override
    public Company findByArticle(ArticleEntityDTO article) {
        Query query = entityManager.createQuery("from Company c join fetch c.users u join fetch u.articles a where a.id = :articleId");
        query.setParameter("articleId", article.getId());
        return getSingleResult(query);
    }

}
