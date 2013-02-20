package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.search.SolrServerProxy;
import nl.codebasesoftware.produx.service.SearchService;
import nl.codebasesoftware.produx.service.SystemPropertyService;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 13:19
 */
@Service
public class SearchServiceImpl implements SearchService {

    private SystemPropertyService systemPropertyService;
    private SolrServerProxy solrServerProxy;
    private static final String RESPONSE_FORMAT = "json";
    private static final Logger LOG = Logger.getLogger(SearchServiceImpl.class);

    @Autowired
    public SearchServiceImpl(SystemPropertyService systemPropertyService, SolrServerProxy solrServerProxy) {
        this.systemPropertyService = systemPropertyService;
        this.solrServerProxy = solrServerProxy;
    }

    @Override
    public QueryResponse search(String query) {

        ModifiableSolrParams solrParams = new ModifiableSolrParams();

        solrParams.add("q", query);
        solrParams.add("wt", RESPONSE_FORMAT);
        solrParams.add("facet", "true");
        solrParams.add("facet.field", "category");
        solrParams.add("facet.field", "tags");
        solrParams.add("facet.field", "regions");
        solrParams.add("facet.field", "price");
        solrParams.add("facet.field", "certificate");
        solrParams.add("facet.field", "incompany");

        LOG.debug(String.format("query: %s", solrParams.toString()));

        QueryResponse response = null;

        try {
            response = solrServerProxy.getServer().query(solrParams);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        return response;
    }
}
