package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Category;

import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:13
 */
public interface CourseService {
    List<Category> findCategorizedCourses();
}
