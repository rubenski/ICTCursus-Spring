package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.dao.CategoryHighlightPeriodDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.HighlightedCoursePeriod;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.domain.support.DateRange;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.HighlightedCoursePeriodService;
import nl.codebasesoftware.produx.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class HighlightedCoursePeriodServiceImpl implements HighlightedCoursePeriodService {

    private CategoryDao categoryDao;
    private CourseDao courseDao;
    private CourseService courseService;
    private CategoryHighlightPeriodDao categoryHighlightPeriodDao;
    private Properties properties;

    @Autowired
    public HighlightedCoursePeriodServiceImpl(CategoryDao categoryDao, CourseDao courseDao, CourseService courseService, CategoryHighlightPeriodDao categoryHighlightPeriodDao, Properties properties) {
        this.categoryDao = categoryDao;
        this.courseDao = courseDao;
        this.courseService = courseService;
        this.categoryHighlightPeriodDao = categoryHighlightPeriodDao;
        this.properties = properties;
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

        HighlightedCoursePeriod highlightedCourse = new HighlightedCoursePeriod();
        highlightedCourse.setStartTime(startTime);
        highlightedCourse.setEndTime(endTime);
        highlightedCourse.setCategory(category);
        highlightedCourse.setCourse(course);
        highlightedCourse.setCreated(Calendar.getInstance());

        category.getHighlightedCourses().add(highlightedCourse);

        categoryHighlightPeriodDao.persist(highlightedCourse);

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
        List<HighlightedCoursePeriod> currentAndFutureHighlightedCourses = categoryHighlightPeriodDao.findCurrentAndFutureHighlightedCourses(category.getId(), Calendar.getInstance());
        Calendar startDate = findFirstStartDate(currentAndFutureHighlightedCourses, highlightedCoursesPerCategory);

        // Determine end date
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(startDate.getTime());
        endDate.add(Calendar.DATE, numberOfDays);

        return new DateRange(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HighlightedCoursePeriod> findHighlightedForCompany(long companyId) {
        return categoryHighlightPeriodDao.findCurrentAndFutureHighlightedCoursesForCompany(companyId, Calendar.getInstance());
    }

    private Calendar findFirstStartDate(List<HighlightedCoursePeriod> currentAndFutureHighlightedCourses, int highlightedCoursesPerCategory) {
        Calendar startDate = Calendar.getInstance();
        if (currentAndFutureHighlightedCourses.size() == highlightedCoursesPerCategory) {
            for (HighlightedCoursePeriod highlightedCourse : currentAndFutureHighlightedCourses) {
                if (highlightedCourse.getEndTime().before(startDate)) {
                    startDate = highlightedCourse.getEndTime();
                }
            }
        }
        return startDate;
    }
}
