package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CategoryHighlightPeriodDao;
import nl.codebasesoftware.produx.domain.HighlightedCoursePeriod;
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
@SuppressWarnings("unchecked")
public class HighlightedCoursePeriodDaoJpa extends GenericDaoJpa<HighlightedCoursePeriod> implements CategoryHighlightPeriodDao {


    public HighlightedCoursePeriodDaoJpa() {
        super(HighlightedCoursePeriod.class);
    }

    /**
     * Retrieves all courses that are highlighted on the supplied category that have an end date in the future. Start date is not important.
     */
    @Override
    public List<HighlightedCoursePeriod> findCurrentAndFutureHighlightedCourses(long categoryId, Calendar time) {
        return entityManager.createQuery("from HighlightedCoursePeriod hcoc where hcoc.endTime >= :time")
                .setParameter("time", time, TemporalType.DATE).getResultList();
    }

    @Override
    public List<HighlightedCoursePeriod> findCurrentAndFutureHighlightedCoursesForCompany(long categoryId, long companyId, Calendar time) {
        return entityManager.createQuery("from HighlightedCoursePeriod hc inner join fetch hc.course c inner join fetch c.company comp where hc.endTime >= :time and comp.id = :companyId")
                .setParameter("time", time, TemporalType.DATE)
                .setParameter("companyId", companyId)
                .getResultList();
    }
}
