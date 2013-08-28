package nl.codebasesoftware.produx.search.solrquery.queryitems;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-8-13
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public interface SolrQueryParameters {
    List<String> toQueryParameters();
}
