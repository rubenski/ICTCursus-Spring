package nl.codebasesoftware.produx.search;

import org.apache.solr.common.params.ModifiableSolrParams;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class RangeFilter extends Filter {

    private final Range range;

    public RangeFilter(String field, Range range) {
        super(field);
        this.field = field;
        this.range = range;
    }

    public Range getRange() {
        return range;
    }

    @Override
    public List<String> getUrlTokens() {
        return Arrays.asList(String.format("%s:%s-%s", field, range.getLeft(), range.getRight()));
    }

    @Override
    public ModifiableSolrParams createSolrParams() {
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.add("fq", String.format("%s:[%s TO %s]", getTaggedField(), range.getLeft(), range.getRight()));
        return params;
    }
}
