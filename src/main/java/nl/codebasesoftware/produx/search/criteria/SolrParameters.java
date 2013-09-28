package nl.codebasesoftware.produx.search.criteria;

import org.apache.solr.common.params.ModifiableSolrParams;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 5-9-13
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
public interface SolrParameters {
    ModifiableSolrParams createSolrParams();
    //String createSolrString();
}
