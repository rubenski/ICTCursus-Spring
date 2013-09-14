package nl.codebasesoftware.produx.search.criteria.filter;

import org.apache.solr.common.params.ModifiableSolrParams;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class NormalFilter extends Filter {

    private final Object value;

    public NormalFilter(String field, Object value) {
        super(field);
        this.value = value;
    }

    @Override
    public List<String> getUrlTokens() {
        return Arrays.asList(String.format("%s:%s", field, value));
    }

    @Override
    public ModifiableSolrParams createSolrParams() {
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.add("fq", String.format("%s:\"%s\"", field, value));
        return params;
    }

}