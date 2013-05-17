package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 11-5-13
 * Time: 11:08
 */
public interface HighlightedCourseDao extends GenericDao<HighlightedCourseOnCategory> {
    List<HighlightedCourseOnCategory> findCurrentAndFutureHighlightedCourses(long categoryId, Calendar time);
    List<HighlightedCourseOnCategory> findCurrentAndFutureHighlightedCoursesForCompany(long categoryId, long companyId, Calendar instance);
}
