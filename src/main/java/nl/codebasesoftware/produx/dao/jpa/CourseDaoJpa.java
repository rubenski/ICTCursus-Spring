package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Time;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:41
 */
@Repository
@SuppressWarnings("unchecked")
public class CourseDaoJpa extends GenericDaoJpa<Course> implements CourseDao {

    public CourseDaoJpa() {
        super(Course.class);
    }


    @Override
    public List<Course> findForListing(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            return Collections.emptyList();
        }
        String hql = "select distinct c from Course c " +
                "join fetch c.category cat " +
                "left join fetch c.regions reg " +
                "left join fetch c.tags tag " +
                "where c.id in (:ids)";
        Query query = entityManager.createQuery(hql);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    public List<Long> findIndexableCourseIds(Calendar lastIndexDate) {
        String hql = "select id from Course c where c.lastIndexed is NULL OR c.lastIndexed > :lastIndexDate";
        Query query = entityManager.createQuery(hql);
        query.setParameter("lastIndexDate", lastIndexDate);
        return query.getResultList();
    }


    @Override
    public List<Course> findCourses(long companyId) {
        Query query = entityManager.createQuery("select c from Course c where c.company.id = :companyId");
        query.setParameter("companyId", companyId);
        return query.getResultList();
    }

    @Override
    public Course findFull(Long id) {
        Query query = entityManager.createQuery("select distinct c from Course c " +
                "join fetch c.company " +
                "left join fetch c.regions " +
                "left join fetch c.tags " +
                "left join fetch c.category " +
                "left join fetch c.times " +
                "left join fetch c.dates " +
                "left join fetch c.options opts " +
                "left join fetch opts.category " +
                "left join fetch c.experiences " +
                "left join fetch c.highlightedCoursePeriods " +
                "where c.id = :id");
        query.setParameter("id", id);
        return getSingleResult(query);
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
    public List<Course> findCoursesForCompanyAndCategory(Long companyId, Long categoryId) {
        return entityManager.createQuery("from Course c where c.category.id = :categoryId and c.company.id = :companyId")
                .setParameter("categoryId", categoryId)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Override
    public List<Course> findNonHighlightedCourses(long categoryId, Calendar currentDate) {
        String query = "from Course c " +
                "inner join fetch c.company " +
                "inner join fetch c.category " +
                "left join fetch c.highlightedCoursePeriods cats " +
                "where c.category.id = :categoryId " +
                "and (cats.category.id is null " +
                "   or cats.category.id <> :categoryId " +
                "   or (cats.category.id = :categoryId and :today not between cats.startTime and cats.endTime))" +
                "and c.published = true";

        return entityManager.createQuery(query)
                .setParameter("categoryId", categoryId)
                .setParameter("today", currentDate, TemporalType.DATE).getResultList();
    }

    @Override
    public List<Course> findHighlightedCourses(long categoryId, Calendar time) {
        String query = "from Course c " +
                "inner join fetch c.company " +
                "inner join fetch c.category " +
                "left join fetch c.highlightedCoursePeriods cats " +
                "where c.category.id = :categoryId " +
                "and cats.category.id = :categoryId " +
                "and cats.startTime <= :time " +
                "and cats.endTime > :time " +
                "and c.published = true";

        return entityManager.createQuery(query)
                .setParameter("categoryId", categoryId)
                .setParameter("time", time, TemporalType.DATE).getResultList();
    }

    @Override
    public List<Course> findAllWithCompany() {
        return entityManager.createQuery("from Course c inner join fetch c.company").getResultList();
    }
}
