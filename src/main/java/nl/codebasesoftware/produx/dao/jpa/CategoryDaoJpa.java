package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.domain.Category;
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
        return getSingleResult(entityManager.createQuery("from Category c where c.urlTitle = :name").setParameter("name", name));
    }

    @Override
    public List<Category> findSubCategories(Category category) {
        return entityManager.createQuery("from Category c where c.parent.id = :id").setParameter("id", category.getId()).getResultList();
    }
}
