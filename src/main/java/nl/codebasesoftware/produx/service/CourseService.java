package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.service.helpers.CourseFilter;

import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:13
 */
public interface CourseService {
    public List<Category> findFirstLevelCategories();
    public List<Course> findCourses(CourseFilter filter);
    List<Course> findCourses(Category category);
}
