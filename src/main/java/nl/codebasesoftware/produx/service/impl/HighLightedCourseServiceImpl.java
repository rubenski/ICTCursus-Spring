package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.dao.HighlightedCourseDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;
import nl.codebasesoftware.produx.service.HighlightedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * User: rvanloen
 * Date: 11-5-13
 * Time: 11:05
 */
@Service
public class HighLightedCourseServiceImpl implements HighlightedCourseService {

    private CategoryDao categoryDao;
    private CourseDao courseDao;
    private HighlightedCourseDao highlightedCourseDao;

    @Autowired
    public HighLightedCourseServiceImpl(CategoryDao categoryDao, CourseDao courseDao, HighlightedCourseDao highlightedCourseDao) {
        this.categoryDao = categoryDao;
        this.courseDao = courseDao;
        this.highlightedCourseDao = highlightedCourseDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void addHighlightedCourse(Long courseId, Long categoryId, Date startDate, int numberOfMonths) {
        Category category = categoryDao.find(categoryId);
        Course course = courseDao.find(courseId);

        Calendar startTime = Calendar.getInstance();
        startTime.setTime(startDate);

        Calendar endTime = Calendar.getInstance();
        endTime.setTime(startDate);
        endTime.add(Calendar.MONTH, numberOfMonths);


        HighlightedCourseOnCategory highlightedCourse = new HighlightedCourseOnCategory();
        highlightedCourse.setStartTime(startTime);
        highlightedCourse.setEndTime(endTime);
        highlightedCourse.setCategory(category);
        highlightedCourse.setCourse(course);

        category.getHighlightedCourses().add(highlightedCourse);

        highlightedCourseDao.persist(highlightedCourse);

    }
}
