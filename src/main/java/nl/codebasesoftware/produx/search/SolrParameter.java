package nl.codebasesoftware.produx.search;

import org.apache.solr.common.params.ModifiableSolrParams;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 5-9-13
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
public interface SolrParameter {
    ModifiableSolrParams createSolrParams();
}
