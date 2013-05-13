package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.support.DateRange;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:43
 */
public interface CategoryService {
    Category findByUrlTitle(String name);
    List<Category> findAll();
    DateRange findDateRangeForHighlightStart(long categoryId);
    DateRange findDateRangeForHighlightStart(Category category);
    Category findById(Long categoryId);
}
