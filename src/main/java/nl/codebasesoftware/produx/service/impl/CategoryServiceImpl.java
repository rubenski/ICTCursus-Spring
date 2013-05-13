package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.comparator.NameComparator;
import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;
import nl.codebasesoftware.produx.domain.support.DateRange;
import nl.codebasesoftware.produx.service.CategoryService;
import nl.codebasesoftware.produx.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:44
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;
    private CourseDao courseDao;
    private Properties properties;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, CourseDao courseDao, Properties properties) {
        this.categoryDao = categoryDao;
        this.courseDao = courseDao;
        this.properties = properties;
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByUrlTitle(String name) {
        return categoryDao.findByUrlTitle(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        List<Category> categoryList = categoryDao.findAll();
        Collections.sort(categoryList, new NameComparator());
        return categoryList;
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
        String numberOfMonthsAfterStartDate = properties.getProperty("range.for.highlight.start");
        int highlightedCoursesPerCategory = Integer.parseInt(numberString);
        int numberOfMonths = Integer.parseInt(numberOfMonthsAfterStartDate);

        // Find the first possible start date (iterating over a date sorted list. Last item is closest end date)
        Calendar startDate = findFirstStartDate(category, highlightedCoursesPerCategory);

        // Determine end date
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(startDate.getTime());
        endDate.add(Calendar.MONTH, numberOfMonths);

        return new DateRange(startDate, endDate);
    }



    @Override
    @Transactional(readOnly = true)
    public Category findById(Long categoryId) {
        return categoryDao.find(categoryId);
    }

    private Calendar findFirstStartDate(Category category, int highlightedCoursesPerCategory) {

        Calendar startDate = Calendar.getInstance();
        if (category.getHighlightedCourses().size() == highlightedCoursesPerCategory) {
            Iterator<HighlightedCourseOnCategory> iterator = category.getHighlightedCourses().iterator();
            while (iterator.hasNext()) {
                HighlightedCourseOnCategory highlightedCourseOnCategory = iterator.next();
                if (!iterator.hasNext()) {
                    startDate = highlightedCourseOnCategory.getEndTime();
                }
            }
        }
        return  startDate;
    }
}
