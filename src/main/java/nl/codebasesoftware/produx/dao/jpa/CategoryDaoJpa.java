package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.domain.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:45
 */
@Repository
@SuppressWarnings("unchecked")
public class CategoryDaoJpa extends GenericDaoJpa<Category> implements CategoryDao {

    public CategoryDaoJpa() {
        super(Category.class);
    }


    @Override
    public Category findByUrlTitle(String name) {
        return getSingleResult(entityManager.createQuery("from Category c where c.urlTitle = :name").setParameter("name", name));
    }

    @Override
    public List<Category> findSubCategories(Category category) {
        return entityManager.createQuery("from Category c where c.parent.id = :id").setParameter("id", category.getId()).getResultList();
    }

    @Override
    public List<Category> findFirstLevelCategories() {
        Query query = entityManager.createQuery("select distinct c from Category c left join fetch c.children ch where c.parent is null order by c.name, ch.name");
        return query.getResultList();
    }


}
