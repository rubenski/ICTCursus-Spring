package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Course_;
import nl.codebasesoftware.produx.service.helpers.CourseFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:41
 */
@Repository
public class CourseDaoJpa extends GenericDaoJpa<Course> implements CourseDao {

    public CourseDaoJpa() {
        super(Course.class);
    }

    @Override
    public List<Category> findFirstLevelCategories() {
        Query query = entityManager.createQuery("select distinct c from Category c left join fetch c.children ch where c.parent is null order by c.name");
        return query.getResultList();
    }

    @Override
    public List<Course> findCourses(CourseFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if (!filter.getCategories().isEmpty()) {
            Predicate predicate = root.get(Course_.category).in(filter.getCategories());
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);

        criteriaQuery.where(predicates);
        TypedQuery<Course> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    @Override
    public List<Course> findCourses(Long categoryId) {
        Query queryGood = entityManager.createQuery("select c from Course c join fetch c.company where c.category.id = :categoryId");
        queryGood.setParameter("categoryId", categoryId);
        return queryGood.getResultList();
    }

    @Override
    public List<Course> findCourses(Company company) {
        Query query = entityManager.createQuery("select c from Course c where c.company = :company");
        query.setParameter("company", company);
        return query.getResultList();
    }

    @Override
    public Course findFull(Long id) {
        Query query = entityManager.createQuery("select c from Course c " +
                "join fetch c.company " +
                "left join fetch c.regions " +
                "left join fetch c.tags " +
                "where c.id = :id");
        query.setParameter("id", id);
        return (Course) query.getSingleResult();
    }
}
