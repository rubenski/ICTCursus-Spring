package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.HighlightedCourseDao;
import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 11-5-13
 * Time: 11:08
 */
@Repository
public class HighlightedCourseDaoJpa extends GenericDaoJpa<HighlightedCourseOnCategory> implements HighlightedCourseDao {


    public HighlightedCourseDaoJpa() {
        super(HighlightedCourseOnCategory.class);
    }

    /**
     * Retrieves all courses that are hightlighted on the supplied category that have an end date in the future. Start date is not important.
     */
    @Override
    public List<HighlightedCourseOnCategory> findCurrentAndFutureHighlightedCourses(long categoryId, Calendar time) {
        return entityManager.createQuery("from HighlightedCourseOnCategory hcoc where hcoc.endTime >= :time")
                .setParameter("time", time, TemporalType.DATE).getResultList();
    }

    @Override
    public List<HighlightedCourseOnCategory> findCurrentAndFutureHighlightedCoursesForCompany(long categoryId, long companyId, Calendar time) {
        return entityManager.createQuery("from HighlightedCourseOnCategory hc inner join fetch hc.course c inner join fetch c.company comp where hc.endTime >= :time and comp.id = :companyId")
                .setParameter("time", time, TemporalType.DATE)
                .setParameter("companyId", companyId)
                .getResultList();
    }
}
