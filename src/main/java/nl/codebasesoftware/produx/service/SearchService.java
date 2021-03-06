package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
import nl.codebasesoftware.produx.search.criteria.facet.FacetField;
import nl.codebasesoftware.produx.search.criteria.filter.Filter;
import nl.codebasesoftware.produx.search.result.SearchResult;

import java.util.List;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 13:17
 */
public interface SearchService {
    SearchResult findCoursesForFacets(SearchCriteria criteria, List<String> baseDirs) throws ProduxServiceException;

    FacetField createTagsFacet();

    FacetField createPriceFacet(List<Filter> filterList);

    FacetField createRegionsFacet(List<Filter> filterList);

    SearchResult findCourses(SearchCriteria criteria)  throws ProduxServiceException;

    SearchResult findOtherCourses(CourseEntityDTO course) throws ProduxServiceException;
}
