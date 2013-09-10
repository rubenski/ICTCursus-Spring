package nl.codebasesoftware.produx.search;

import org.apache.solr.common.params.ModifiableSolrParams;

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

    public MultiValueRangeFilter(String field, List<Range> ranges) {
        super(field);
        this.ranges = ranges;
    }

    @Override
    public ModifiableSolrParams createSolrParams() {
        ModifiableSolrParams params = new ModifiableSolrParams();
        StringBuilder builder = new StringBuilder();
        builder.append(field).append(":").append("(");
        for (int i = 0; i < ranges.size(); i++){
            String s = String.format("[%s TO %s]", ranges.get(i).getLeft(), ranges.get(i).getRight());
            builder.append(s);
            if(i < ranges.size() -1){
                builder.append(" OR ");
            }
        }
        builder.append(")");
        params.add("fq", builder.toString());
        return params;
    }

    @Override
    public String getUrlToken() {
        StringBuilder builder = new StringBuilder();
        builder.append(field).append(":");
        for (int i = 0; i < ranges.size(); i++){
            String s = String.format("%s-%s", ranges.get(i).getLeft(), ranges.get(i).getRight());
            builder.append(s);
            if(i < ranges.size() -1){
                builder.append(",");
            }
        }
        return builder.toString();
    }
}
