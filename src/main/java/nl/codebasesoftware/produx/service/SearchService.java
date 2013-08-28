package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.search.SearchCriteria;
import nl.codebasesoftware.produx.search.SearchResult;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 13:17
 */
public interface SearchService {
    SearchResult findCourses(SearchCriteria criteria) throws ProduxServiceException;
}
