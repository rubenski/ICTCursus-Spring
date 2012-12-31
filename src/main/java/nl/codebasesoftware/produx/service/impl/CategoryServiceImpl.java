package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.comparator.NameComparator;
import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:44
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category findByName(String name)  {
        return categoryDao.findByName(name);
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = categoryDao.findAll();
        Collections.sort(categoryList, new NameComparator());
        return categoryList;
    }
}
