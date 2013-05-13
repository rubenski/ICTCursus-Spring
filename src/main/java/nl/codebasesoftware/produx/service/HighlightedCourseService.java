package nl.codebasesoftware.produx.service;

import java.util.Date;

/**
 * User: rvanloen
 * Date: 11-5-13
 * Time: 11:04
 */
public interface HighlightedCourseService {
    void addHighlightedCourse(Long courseId, Long categoryId, Date startDate, int numberOfMonths);
}
