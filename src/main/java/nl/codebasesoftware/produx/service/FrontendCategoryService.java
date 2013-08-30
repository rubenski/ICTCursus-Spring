package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.search.SearchResult;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 30-8-13
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */
public interface FrontendCategoryService {

    SearchResult findCourses(CategoryEntityDTO category);
}
