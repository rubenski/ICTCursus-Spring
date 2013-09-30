package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.conversion.QueryResponseToSearchResultConverter;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
import nl.codebasesoftware.produx.search.criteria.facet.*;
import nl.codebasesoftware.produx.search.criteria.filter.Filter;
import nl.codebasesoftware.produx.search.result.SearchResult;
import nl.codebasesoftware.produx.service.SearchService;
import nl.codebasesoftware.produx.service.SolrService;
import nl.codebasesoftware.produx.util.Properties;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 13:19
 */
@Service
public class SearchServiceImpl implements SearchService {


    private static final Logger LOG = Logger.getLogger(SearchServiceImpl.class);
    private SolrService solrService;
    private ConversionService conversionService;
    private QueryResponseToSearchResultConverter converter;

    @Autowired
    public SearchServiceImpl(SolrService solrService, ConversionService conversionService, QueryResponseToSearchResultConverter converter, Properties properties) {
        this.solrService = solrService;
        this.conversionService = conversionService;
        this.converter = converter;
    }

    @Override
    public SearchResult findCoursesForFacets(SearchCriteria criteria, List<String> baseDirs) throws ProduxServiceException {
        ModifiableSolrParams modifiableSolrParams = conversionService.convert(criteria, SolrQuery.class);
        QueryResponse response = solrService.search(modifiableSolrParams);
        return converter.convertWithFacets(response, criteria, baseDirs);
    }

    @Override
    public SearchResult findCourses(SearchCriteria criteria) throws ProduxServiceException {
        ModifiableSolrParams modifiableSolrParams = conversionService.convert(criteria, SolrQuery.class);
        QueryResponse response = solrService.search(modifiableSolrParams);
        return converter.convert(response, criteria);
    }


    public FacetField createPriceFacet(List<Filter> filterList) {
        RangeFacetField priceFacet = new RangeFacetField("price", 0, 300000, 50000, FacetSortingBehavior.NATURAL_ORDER);
        priceFacet.addOtherBehavior(RangeFacetOtherBehavior.AFTER);
        for (Filter filter : filterList) {
            if (filter.getTag().equals("_price")) {
                priceFacet.addExcludedFilter("_price");
            }
        }
        return priceFacet;
    }

    public FacetField createTagsFacet() {
        NormalFacetField tagsFacet = new NormalFacetField("tags", FacetSortingBehavior.COUNT);
        tagsFacet.setMinCount(1);
        return tagsFacet;
    }

    public FacetField createRegionsFacet(List<Filter> filterList) {
        NormalFacetField regionFacet = new NormalFacetField("regions", FacetSortingBehavior.NATURAL_ORDER);
        for (Filter filter : filterList) {
            if (filter.getTag().equals("_regions")) {
                regionFacet.addExcludedFilter("_regions");
            }
        }
        regionFacet.setMinCount(1);
        return regionFacet;
    }


}
