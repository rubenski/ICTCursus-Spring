package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.service.helpers.CourseFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
    public List<Course> findBasic(List<Long> ids) {
        if(ids == null || ids.size() == 0){
            return Collections.emptyList();
        }
        String hql = "from Course c join fetch c.category cat where c.id in (:ids)";
        Query query = entityManager.createQuery(hql);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    public List<Long> findIndexableCourseIds(Calendar lastIndexDate){
        String hql = "select id from Course c where c.lastIndexed is NULL OR c.lastIndexed > :lastIndexDate";
        Query query = entityManager.createQuery(hql);
        query.setParameter("lastIndexDate", lastIndexDate);
        return query.getResultList();
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
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Course findFull(Long id) {
        Query query = entityManager.createQuery("select c from Course c " +
                "join fetch c.company " +
                "left join fetch c.regions " +
                "left join fetch c.tags " +
                "left join fetch c.category " +
                "left join fetch c.times " +
                "left join fetch c.dates " +
                "left join fetch c.options " +
                "where c.id = :id");
        query.setParameter("id", id);
        Course result = getSingleResult(query);
        return result;
    }

    @Override
    public List<Time> findCourseTimes() {
        Query query = entityManager.createQuery("from Time t order by t.displayRank");
        return query.getResultList();
    }

    @Override
    public Time getCourseTime(long id) {
        Query query = entityManager.createQuery("from Time t where t.id = :id");
        query.setParameter("id", id);
        return (Time) query.getSingleResult();
    }
}
