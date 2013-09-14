package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.conversion.QueryResponseToSearchResultConverter;
import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
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

    public SearchResult findCategoryCourses(SearchCriteria criteria, CategoryEntityDTO category) throws ProduxServiceException {
        ModifiableSolrParams modifiableSolrParams = conversionService.convert(criteria, SolrQuery.class);
        LOG.debug("Firing Solr query");
        LOG.debug(modifiableSolrParams);
        QueryResponse response = solrService.search(modifiableSolrParams);
        SearchResult convert = converter.convert(response, criteria, category);
        return convert;
    }

}
