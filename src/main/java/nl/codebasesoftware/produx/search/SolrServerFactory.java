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

    /*
        HttpSolrServer is thread-safe and if you are using the following constructor,
        you *MUST* re-use the same instance for all requests.  If instances are created on
        the fly, it can cause a connection leak. The recommended practice is to keep a
        static instance of HttpSolrServer per solr server url and share it for all requests.
        See https://issues.apache.org/jira/browse/SOLR-861 for more details

        In this application the Solr server is defined as a singleton scoped Spring bean, which also works.
    */

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
