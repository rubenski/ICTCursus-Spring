package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.formdata.BindableCourse;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

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
    void update(BindableCourse bindableCourse);
    Course insert(BindableCourse bindableCourse);
    List<Course> findIndexableCourses();
    List<Course> findBasic(List<Long> ids);
    List<Time> findCourseTimes();
    List<String> getDates(Long id);
}
