package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.*;
import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.SystemPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:39
 */
@Service
public class CourseServiceImpl implements CourseService {


    private CourseDao courseDao;
    private CategoryDao categoryDao;
    private RegionDao regionDao;
    private TagDao tagDao;
    private SystemPropertyService systemPropertyService;
    private CourseOptionDao optionDao;
    private ConversionService conversionService;

    private Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    public CourseServiceImpl(CourseDao courseDao, CategoryDao categoryDao, RegionDao regionDao, TagDao tagDao,
                             SystemPropertyService systemPropertyService, CourseOptionDao optionDao, ConversionService conversionService) {
        this.courseDao = courseDao;
        this.categoryDao = categoryDao;
        this.regionDao = regionDao;
        this.tagDao = tagDao;
        this.systemPropertyService = systemPropertyService;
        this.optionDao = optionDao;
        this.conversionService = conversionService;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Category> findFirstLevelCategories() {
        return courseDao.findFirstLevelCategories();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findCourses(Long categoryId) {
        return courseDao.findCourses(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findByCompany(Company company) {
        return courseDao.findCourses(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findBasic(List<Long> ids) {
        return courseDao.findBasic(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Time> findCourseTimes() {
        return courseDao.findCourseTimes();
    }

    @Override
    @Transactional(readOnly = true)
    public Course findFull(Long id) {
        return courseDao.findFull(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Course save(BindableCourse bindableCourse) {
        Course course = conversionService.convert(bindableCourse, Course.class);

        if (bindableCourse.isPublished() && !course.wasPreviouslyPublished()) {
            course.setFirstPublished(Calendar.getInstance());
        }

        courseDao.persist(course);

        return course;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findIndexableCourses() {
        Calendar calendar = systemPropertyService.lastSolrUpdateDate();
        List<Long> indexableCourseIds = courseDao.findIndexableCourseIds(calendar);
        List<Course> indexableCourses = new ArrayList<Course>();
        for (Long indexableCourseId : indexableCourseIds) {
            Course course = courseDao.findFull(indexableCourseId);
            indexableCourses.add(course);
        }

        return indexableCourses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findNonHighlightedCoursesForCompanyAndCategory(Company currentlyLoggedInCompany, Category category) {
        List<Course> highLightedCourses = courseDao.findHighlightedCoursesForCompanyAndCategory(currentlyLoggedInCompany.getId(), category.getId());
        List<Course> courses = courseDao.findCoursesForCompanyAndCategory(currentlyLoggedInCompany.getId(), category.getId());
        List<Course> nonHighLighted = new ArrayList<Course>();

        courses.removeAll(highLightedCourses);

        return courses;

    }


}
