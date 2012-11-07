package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Category_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:45
 */
@Repository
public class CategoryDaoJpa extends GenericDaoJpa<Category> implements CategoryDao {

    public CategoryDaoJpa() {
        super(Category.class);
    }


    @Override
    public Category findByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        Predicate predicate = builder.equal(root.get(Category_.urlTitle), name);
        query.where(predicate);
        TypedQuery<Category> typedQuery = entityManager.createQuery(query);
        return getSingleResult(typedQuery);
    }

    @Override
    public List<Category> findSubCategories(Category category) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        Predicate predicate = builder.equal(root.get(Category_.parent), category);
        query.where(predicate);
        TypedQuery<Category> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
