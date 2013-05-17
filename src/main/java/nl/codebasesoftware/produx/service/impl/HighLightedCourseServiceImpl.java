package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.dao.HighlightedCourseDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;
import nl.codebasesoftware.produx.domain.support.DateRange;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.HighlightedCourseService;
import nl.codebasesoftware.produx.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: rvanloen
 * Date: 11-5-13
 * Time: 11:05
 */
@Service
public class HighLightedCourseServiceImpl implements HighlightedCourseService {

    private CategoryDao categoryDao;
    private CourseService courseService;
    private HighlightedCourseDao highlightedCourseDao;
    private Properties properties;

    @Autowired
    public HighLightedCourseServiceImpl(CategoryDao categoryDao, CourseService courseService, HighlightedCourseDao highlightedCourseDao, Properties properties) {
        this.categoryDao = categoryDao;
        this.courseService = courseService;
        this.highlightedCourseDao = highlightedCourseDao;
        this.properties = properties;
    }

    @Override
    @Transactional(readOnly = false)
    public void addHighlightedCourse(Long courseId, Long categoryId, Date startDate, int numberOfMonths) {
        Category category = categoryDao.find(categoryId);
        Course course = courseService.findById(courseId);

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

    @Override
    @Transactional(readOnly = true)
    public DateRange findDateRangeForHighlightStart(long categoryId) {
        Category category = categoryDao.find(categoryId);
        return findDateRangeForHighlightStart(category);
    }

    @Override
    @Transactional(readOnly = true)
    public DateRange findDateRangeForHighlightStart(Category category) {
        String numberString = properties.getProperty("highlighted.courses.per.category");
        String numberOfDaysAfterStartDate = properties.getProperty("range.for.highlight.start");
        int highlightedCoursesPerCategory = Integer.parseInt(numberString);
        int numberOfDays = Integer.parseInt(numberOfDaysAfterStartDate);

        // Find the first possible start date (iterating over a date sorted list. Last item is closest end date)
        List<HighlightedCourseOnCategory> currentAndFutureHighlightedCourses = highlightedCourseDao.findCurrentAndFutureHighlightedCourses(category.getId(), Calendar.getInstance());
        Calendar startDate = findFirstStartDate(currentAndFutureHighlightedCourses, highlightedCoursesPerCategory);

        // Determine end date
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(startDate.getTime());
        endDate.add(Calendar.DATE, numberOfDays);

        return new DateRange(startDate, endDate);
    }

    public List<HighlightedCourseOnCategory> findCurrentAndFutureHighlightedCoursesForCompany(long categoryId, long companyId){
        return highlightedCourseDao.findCurrentAndFutureHighlightedCoursesForCompany(categoryId, companyId, Calendar.getInstance());
    }

    private Calendar findFirstStartDate(List<HighlightedCourseOnCategory> currentAndFutureHighlightedCourses, int highlightedCoursesPerCategory) {
        Calendar startDate = Calendar.getInstance();
        if (currentAndFutureHighlightedCourses.size() == highlightedCoursesPerCategory) {
            for (HighlightedCourseOnCategory highlightedCourse : currentAndFutureHighlightedCourses) {
                if(highlightedCourse.getEndTime().before(startDate)){
                    startDate = highlightedCourse.getEndTime();
                }
            }
        }
        return startDate;
    }
}
