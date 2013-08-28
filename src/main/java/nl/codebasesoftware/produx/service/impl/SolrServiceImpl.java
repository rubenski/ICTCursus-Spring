package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.conversion.QueryResponseToSearchResultConverter;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.search.SearchResult;
import nl.codebasesoftware.produx.search.SolrServerFactory;
import nl.codebasesoftware.produx.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 9-8-13
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SolrServiceImpl implements SolrService {

    private SolrServerFactory solrServerFactory;
    private QueryResponseToSearchResultConverter responseConverter;

    @Autowired
    public SolrServiceImpl(SolrServerFactory solrServerFactory,
                           QueryResponseToSearchResultConverter responseConverter) {
        this.solrServerFactory = solrServerFactory;
        this.responseConverter = responseConverter;
    }

    @Override
    public SearchResult search(SolrParams params) throws ProduxServiceException {

        QueryResponse response;

        try {
            response = solrServerFactory.getServer().query(params);
        } catch (SolrServerException e) {
            throw new ProduxServiceException("Solr server couldn't be reached", e);
        }

        return responseConverter.convert(response);
    }
}
