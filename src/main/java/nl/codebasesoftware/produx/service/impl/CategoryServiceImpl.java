package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.comparator.NameComparator;
import nl.codebasesoftware.produx.dao.CategoryDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.search.Filter;
import nl.codebasesoftware.produx.search.SearchCriteria;
import nl.codebasesoftware.produx.service.CategoryService;
import nl.codebasesoftware.produx.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private SearchService searchService;


    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, SearchService searchService) {
        this.categoryDao = categoryDao;
        this.searchService = searchService;
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

    @Override
    public String generateUrl(CategoryEntityDTO cat, SearchCriteria criteria) {
        StringBuilder builder = new StringBuilder();
        builder.append("/").append(cat.getUrlTitle()).append("/");


        List<Filter> filters = criteria.getFilters();
        for (int i = 0; i < filters.size(); i++) {
            Filter filter = filters.get(i);
            if (!filter.getField().equals("category")) {
                builder.append(filter.getUrlToken());
                if (i < filters.size() - 1) {
                    builder.append("_");
                }
            }

        }

        return builder.toString();
    }


}
