package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.domain.support.DateRange;
import nl.codebasesoftware.produx.search.SearchCriteria;
import nl.codebasesoftware.produx.search.SearchResult;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:43
 */
public interface CategoryService {
    Category findByUrlTitle(String name);
    List<Category> findAll();
    Category findById(Long categoryId);
}
