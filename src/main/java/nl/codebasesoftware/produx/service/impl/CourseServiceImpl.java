package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.*;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Time;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.SystemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:39
 */
@Service
public class CourseServiceImpl implements CourseService {


    private CourseDao courseDao;
    private SystemPropertyService systemPropertyService;
    private ConversionService conversionService;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao,
                             SystemPropertyService systemPropertyService,
                             ConversionService conversionService) {
        this.courseDao = courseDao;
        this.systemPropertyService = systemPropertyService;
        this.conversionService = conversionService;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Category> findFirstLevelCategories() {
        return courseDao.findFirstLevelCategories();
    }


    @Override
    @Transactional(readOnly = true)
    public List<Course> findCurrentlyHighlightedCourses(long categoryId) {
        return courseDao.findCurrentlyHighlightedCourses(categoryId, Calendar.getInstance());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findNonHighlightedCourses(long categoryId) {
        return courseDao.findNonHighlightedCourses(categoryId, Calendar.getInstance());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findByCompany(Company company) {
        return courseDao.findCourses(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListingCourseDTO> findForListing(List<Long> ids) {
        List<Course> list = courseDao.findForListing(ids);
        List<ListingCourseDTO> listingCourses = new ArrayList<>();
        for (Course course : list) {
            listingCourses.add(course.toListingCourseDTO());
        }
        return listingCourses;
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
    public List<CourseEntityDTO> findIndexableCourses() {
        Calendar calendar = systemPropertyService.lastSolrUpdateDate();
        List<Long> indexableCourseIds = courseDao.findIndexableCourseIds(calendar);
        List<CourseEntityDTO> indexableCourses = new ArrayList<>();
        for (Long indexableCourseId : indexableCourseIds) {
            Course course = courseDao.findFull(indexableCourseId);
            indexableCourses.add(course.toDTO());
        }

        return indexableCourses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findNonHighlightedCoursesForCompanyAndCategory(Company currentlyLoggedInCompany, Category category) {
        List<Course> highLightedCourses = findCoursesForCompanyAndCategory(category.getId(), currentlyLoggedInCompany.getId());
        List<Course> courses = courseDao.findCoursesForCompanyAndCategory(currentlyLoggedInCompany.getId(), category.getId());
        courses.removeAll(highLightedCourses);

        return courses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findCoursesForCompanyAndCategory(long categoryId, long companyId){
        return courseDao.findCoursesForCompanyAndCategory(categoryId, companyId);
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(Long courseId) {
        return courseDao.find(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAllWithCompany() {
        return courseDao.findAllWithCompany();
    }
}
