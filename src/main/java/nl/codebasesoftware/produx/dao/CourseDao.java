package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.service.helpers.CourseFilter;
import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:39
 */
public interface CourseDao extends GenericDao<Course> {
    List<Category> findFirstLevelCategories();
    List<Course> findCourses(CourseFilter filter);
    List<Course> findCourses(Category category);
}
