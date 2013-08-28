package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.search.SearchCriteria;
import nl.codebasesoftware.produx.search.SearchResult;
import nl.codebasesoftware.produx.service.SearchService;
import nl.codebasesoftware.produx.service.SolrService;
import org.apache.log4j.Logger;
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

    @Autowired
    public SearchServiceImpl(SolrService solrService, ConversionService conversionService) {
        this.solrService = solrService;
        this.conversionService = conversionService;
    }


    @Override
    public SearchResult findCourses(SearchCriteria criteria) throws ProduxServiceException {
        ModifiableSolrParams modifiableSolrParams = conversionService.convert(criteria, ModifiableSolrParams.class);
        SearchResult result = solrService.search(modifiableSolrParams);
        return result;
    }
}
