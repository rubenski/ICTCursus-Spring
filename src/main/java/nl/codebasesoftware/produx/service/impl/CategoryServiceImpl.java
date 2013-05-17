package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.comparator.NameComparator;
import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;
import nl.codebasesoftware.produx.service.CategoryService;
import nl.codebasesoftware.produx.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


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
    public Category findById(Long categoryId) {
        return categoryDao.find(categoryId);
    }


}
