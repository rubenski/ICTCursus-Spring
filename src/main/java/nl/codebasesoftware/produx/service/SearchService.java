package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
import nl.codebasesoftware.produx.search.result.SearchResult;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 13:17
 */
public interface SearchService {
    SearchResult findCategoryCourses(SearchCriteria criteria, CategoryEntityDTO category) throws ProduxServiceException;
}
