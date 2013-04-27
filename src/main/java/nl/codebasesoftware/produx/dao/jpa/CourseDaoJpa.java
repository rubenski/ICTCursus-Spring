package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
        Query queryGood = entityManager.createQuery("select c from Course c join fetch c.company comp join fetch c.category cat where c.category.id = :categoryId");
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

    @Override
    public List<CourseDate> findDates(Long id) {
        String hql = "from CourseDate cd where cd.course.id = :id order by cd.startDate";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        return query.getResultList();
    }


}
