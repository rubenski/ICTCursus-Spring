package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.formdata.BindableCourse;

import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:13
 */
public interface CourseService {
    List<Course> findByCompany(Company company);
    List<Category> findFirstLevelCategories();
    List<Course> findCourses(Long categoryId);
    Course findFull(Long id);
    Course save(BindableCourse bindableCourse);
    List<Course> findIndexableCourses();
    List<Course> findBasic(List<Long> ids);
    List<Time> findCourseTimes();
    List<Course> findAll();
    List<Course> findNonHighlightedCoursesForCompanyAndCategory(Company currentlyLoggedInCompany, Category category);

}
