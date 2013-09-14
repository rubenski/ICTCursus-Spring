package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.TagDao;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * User: rvanloen
 * Date: 28-9-12
 * Time: 1:43
 */
@Repository
@SuppressWarnings("unchecked")
public class TagDaoJpa extends GenericDaoJpa<Tag> implements TagDao {

    public TagDaoJpa() {
        super(Tag.class);
    }

    public TagDaoJpa(Class<Tag> type) {
        super(type);
    }

    @Override
    public List<Tag> findTagsForCourses(Collection<Course> courses) {
        String jpql = "select t from Tag t inner join fetch t.courses c where c in (:courses)";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public Tag findByName(String tagName) {
        String jpql = "from Tag t where t.name = :tagName";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("tagName", tagName);
        return getSingleResult(query);
    }

    @Override
    public List<Tag> findBySubString(String tagName) {
        String jpql = "from Tag t where t.name like :tagName";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("tagName", "%" + tagName + "%");
        return query.getResultList();
    }

}
