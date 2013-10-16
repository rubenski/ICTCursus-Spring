package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Time;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:39
 */
public interface CourseDao extends GenericDao<Course> {

    List<Course> findCourses(long companyId);

    Course findFull(Long id);

    List<Long> findIndexableCourseIds(Calendar lastIndexDate);

    List findForListing(List<Long> ids);

    List<Time> findCourseTimes();

    Time getCourseTime(long id);

    List<Course> findCoursesForCompanyAndCategory(Long companyId, Long categoryId);

    List<Course> findHighlightedCourses(long categoryId, Calendar time);

    List<Course> findNonHighlightedCourses(long categoryId, Calendar currentDate);

    List<Course> findAllWithCompany();
}
