package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.HighlightedCoursePeriod;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 11-5-13
 * Time: 11:08
 */
public interface CategoryHighlightPeriodDao extends GenericDao<HighlightedCoursePeriod> {
    List<HighlightedCoursePeriod> findCurrentAndFutureHighlightedCourses(long categoryId, Calendar time);

    List<HighlightedCoursePeriod> findCurrentAndFutureHighlightedCoursesForCompany(long categoryId, long companyId, Calendar instance);
}
