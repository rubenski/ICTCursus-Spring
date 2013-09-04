package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.conversion.QueryResponseToSearchResultConverter;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.search.FacetSortingBehavior;
import nl.codebasesoftware.produx.search.RangeFacet;
import nl.codebasesoftware.produx.search.SearchCriteria;
import nl.codebasesoftware.produx.search.SearchResult;
import nl.codebasesoftware.produx.search.solrquery.RangeFacetOtherBehavior;
import nl.codebasesoftware.produx.service.SearchService;
import nl.codebasesoftware.produx.service.SolrService;
import nl.codebasesoftware.produx.util.Properties;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

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
    private Properties properties;

    @Autowired
    public SearchServiceImpl(SolrService solrService, ConversionService conversionService, QueryResponseToSearchResultConverter converter, Properties properties) {
        this.solrService = solrService;
        this.conversionService = conversionService;
        this.converter = converter;
        this.properties = properties;
    }

    public SearchResult findCategoryCourses(Category category, int page, String baseUrl) {

        int resultsPerPage = properties.getSearchResultsPerPage();

        RangeFacet rangeFacet = new RangeFacet("price", 0, 300000, 50000, FacetSortingBehavior.COUNT);
        rangeFacet.addOtherBehavior(RangeFacetOtherBehavior.AFTER);
        rangeFacet.setMinCount(1);

        SearchCriteria criteria = new SearchCriteria.Builder()
                .addCategory(category.toDTO())
                .addRangeFacetField(rangeFacet)
                .setStart(page * resultsPerPage)
                .setRows(resultsPerPage)
                .build();

        QueryResponse response = null;

        try {
            response = findCourses(criteria);
        } catch (ProduxServiceException e) {
            e.printStackTrace();
        }

        return converter.convert(response, baseUrl);
    }


    private QueryResponse findCourses(SearchCriteria criteria) throws ProduxServiceException {
        ModifiableSolrParams modifiableSolrParams = conversionService.convert(criteria, ModifiableSolrParams.class);
        QueryResponse response = solrService.search(modifiableSolrParams);
        return response;
    }
}
