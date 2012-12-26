package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.dao.RegionDao;
import nl.codebasesoftware.produx.dao.TagDao;
import nl.codebasesoftware.produx.domain.*;
import nl.codebasesoftware.produx.formdata.BindableCourse;
import nl.codebasesoftware.produx.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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


    @Autowired
    public CourseServiceImpl(CourseDao courseDao, CategoryDao categoryDao, RegionDao regionDao, TagDao tagDao) {
        this.courseDao = courseDao;
        this.categoryDao = categoryDao;
        this.regionDao = regionDao;
        this.tagDao = tagDao;
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
    public List<Course> findByCompany(Company company) {
        return courseDao.findCourses(company);
    }

    @Override
    public Course findFull(Long id) {
        return courseDao.findFull(id);
    }

    @Override
    @Transactional
    public void update(BindableCourse bindableCourse) {

        Course existingCourse = courseDao.findFull(bindableCourse.getId());
        if (existingCourse == null) {
            return;
        }

        Course course = applyBindableCourseToCourse(bindableCourse, existingCourse);

        courseDao.persist(course);


    }

    private Course applyBindableCourseToCourse(BindableCourse bindableCourse, Course course) {

        // Get the category
        Category category = categoryDao.find(bindableCourse.getCategory());

        // Get the company
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = (UserProfile) authentication.getPrincipal();
        Company company = userProfile.getCompany();

        // Get the regions
        for (Long id : bindableCourse.getRegions()) {
            Region region = regionDao.find(id);
            course.addRegion(region);
        }

        for (String tagName : bindableCourse.getTags()) {
            Tag tag = tagDao.findByName(tagName);
            if(tag == null){
                tag = new Tag();
                tag.setName(tagName);
                tagDao.persist(tag);
            }
            course.addTag(tag);
        }

        course.setId(bindableCourse.getId());
        course.setCategory(category);
        course.setShortDescription(bindableCourse.getShortDescription());
        course.setCompany(company);
        course.setDuration(bindableCourse.getDuration());
        course.setLastUpdated(new Date());
        course.setName(bindableCourse.getName());
        course.setPrice(bindableCourse.getPrice());
        course.setLongDescription(bindableCourse.getLongDescription());

        return course;
    }


}
