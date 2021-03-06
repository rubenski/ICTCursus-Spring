package nl.codebasesoftware.produx.search.criteria.filter;

import org.apache.solr.common.params.ModifiableSolrParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */
public class MultiValueRangeFilter extends Filter {

    private final List<Range> ranges;

    public MultiValueRangeFilter(String solrField, String urlField, List<Range> ranges) {
        super(solrField, urlField);
        this.ranges = ranges;
    }

    @Override
    public ModifiableSolrParams createSolrParams() {
        ModifiableSolrParams params = new ModifiableSolrParams();
        StringBuilder builder = new StringBuilder();
        builder.append(getTaggedField()).append(":").append("(");
        for (int i = 0; i < ranges.size(); i++) {
            String s = String.format("[%s TO %s]", ranges.get(i).getLeft(), ranges.get(i).getRight());
            builder.append(s);
            if (i < ranges.size() - 1) {
                builder.append(" OR ");
            }
        }
        builder.append(")");

        params.add("fq", builder.toString());
        return params;
    }

    @Override
    public List<String> getUrlTokens() {
        List<String> tokens = new ArrayList<>();
        for (Range range : ranges) {
            StringBuilder token = new StringBuilder();
            token.append(urlField).append(":");
            String value = String.format("%s-%s", range.getLeft(), range.getRight());
            token.append(value);
            tokens.add(token.toString());
        }
        return tokens;
    }

}
