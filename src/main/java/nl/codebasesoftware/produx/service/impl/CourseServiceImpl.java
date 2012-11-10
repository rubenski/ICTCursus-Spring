package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.helpers.CourseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:39
 */
@Service
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    @Transactional
    public List<Category> findFirstLevelCategories() {
        return courseDao.findFirstLevelCategories();
    }

    @Override
    @Transactional
    public List<Course> findCourses(CourseFilter filter) {
        return courseDao.findCourses(filter);
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public List<Course> findCourses(Long categoryId){
        return courseDao.findCourses(categoryId);
    }

    @Override
    public List<Course> findByCompany(Company company) {
        return courseDao.findCourses(company);
    }

    @Override
    public Course findById(Long id) {
        return courseDao.find(id);
    }
}
