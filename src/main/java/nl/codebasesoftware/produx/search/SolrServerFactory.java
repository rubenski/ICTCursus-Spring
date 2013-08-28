package nl.codebasesoftware.produx.search;

import nl.codebasesoftware.produx.service.SystemPropertyService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 18:54
 */
@Component
public class SolrServerFactory {

    private SolrServer server;
    private SystemPropertyService systemPropertyService;

    @Autowired
    public SolrServerFactory(SystemPropertyService systemPropertyService) {
        this.systemPropertyService = systemPropertyService;
    }

    public SolrServer getServer(){
        if(server == null){
            String url = systemPropertyService.findByKey("solr.url");
            if(url == null){
                throw new IllegalArgumentException("'solr.url' not found in the system properties table!");
            }
            server = new HttpSolrServer(url);
        }
        return server;
    }
}
