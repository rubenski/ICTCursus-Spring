package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.*;
import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.SystemPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private CategoryDao categoryDao;
    private RegionDao regionDao;
    private TagDao tagDao;
    private SystemPropertyService systemPropertyService;
    private CourseOptionDao optionDao;

    private Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    public CourseServiceImpl(CourseDao courseDao, CategoryDao categoryDao, RegionDao regionDao, TagDao tagDao,
                             SystemPropertyService systemPropertyService, CourseOptionDao optionDao) {
        this.courseDao = courseDao;
        this.categoryDao = categoryDao;
        this.regionDao = regionDao;
        this.tagDao = tagDao;
        this.systemPropertyService = systemPropertyService;
        this.optionDao = optionDao;
    }


    @Override
    @Transactional
    public List<Category> findFirstLevelCategories() {
        return courseDao.findFirstLevelCategories();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Course> findCourses(Long categoryId) {
        return courseDao.findCourses(categoryId);
    }

    @Override
    @Transactional
    public List<Course> findByCompany(Company company) {
        return courseDao.findCourses(company);
    }

    @Override
    @Transactional
    public List<Course> findBasic(List<Long> ids){
        return courseDao.findBasic(ids);
    }

    @Override
    public List<Time> findCourseTimes() {
        return courseDao.findCourseTimes();
    }

    @Override
    public Course findFull(Long id) {
        return courseDao.findFull(id);
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void update(BindableCourse bindableCourse) {

        Course course = courseDao.findFull(bindableCourse.getId());
        if (course == null) {
            return;
        }

        applyBindableCourseToCourse(bindableCourse, course);
        courseDao.persist(course);
    }

    @Override
    @Transactional
    public Course insert(BindableCourse bindableCourse) {
        Course course = new Course();
        applyBindableCourseToCourse(bindableCourse, course);
        courseDao.persist(course);
        return course;
    }

    @Override
    @Transactional
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


    private void applyBindableCourseToCourse(BindableCourse bindableCourse, Course course) {

        // Get the category
        Category category = categoryDao.find(bindableCourse.getCategory());

        // Get the company
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = (UserProfile) authentication.getPrincipal();
        Company company = userProfile.getCompany();

        // Get the regions
        Set<Region> newRegions = new HashSet<Region>();
        if (bindableCourse.getRegions() != null) {
            for (Long id : bindableCourse.getRegions()) {
                Region region = regionDao.find(id);
                newRegions.add(region);
            }
        }
        course.setRegions(newRegions);

        // Get the course times
        Set<Time> newTimes = new HashSet<Time>();
        for (Long timeId : bindableCourse.getTimes()) {
            newTimes.add(courseDao.getCourseTime(timeId));
        }
        course.setTimes(newTimes);

        // Get course options
        Set<CourseOption> options = new HashSet<CourseOption>();
        for (Long optionId : bindableCourse.getOptions()) {
            CourseOption courseOption = optionDao.find(optionId);
            options.add(courseOption);
        }
        course.setOptions(options);

        Set<Tag> newTags = new HashSet<Tag>();
        for (String tagName : bindableCourse.getTags()) {
            Tag tag = tagDao.findByName(tagName);
            if (tag == null) {
                tag = new Tag();
                tag.setName(tagName);
                tagDao.persist(tag);
            }
            newTags.add(tag);
        }
        course.setTags(newTags);

        course.setId(bindableCourse.getId());
        course.setCategory(category);
        course.setShortDescription(bindableCourse.getShortDescription());
        course.setCompany(company);
        course.setDuration(bindableCourse.getDuration());
        course.setLastUpdated(Calendar.getInstance());
        course.setName(bindableCourse.getName());
        course.setPrice(bindableCourse.getPriceAsLong());
        course.setLongDescription(bindableCourse.getLongDescription());
        course.setCertificate(bindableCourse.isCertificate());
        course.setCertificateText(bindableCourse.getCertificateText());
    }


}
