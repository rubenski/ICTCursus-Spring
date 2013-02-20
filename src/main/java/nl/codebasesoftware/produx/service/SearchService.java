package nl.codebasesoftware.produx.service;

import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 13:17
 */
public interface SearchService {
    QueryResponse search(String query);
}
