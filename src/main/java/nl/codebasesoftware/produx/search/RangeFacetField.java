package nl.codebasesoftware.produx.search;

import org.apache.solr.common.params.ModifiableSolrParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class RangeFacetField extends FacetField {

    private Integer start;
    private Integer end;
    private Integer gap;
    private List<RangeFacetOtherBehavior> otherBehaviors = new ArrayList<>();

    public RangeFacetField(String field, Integer start, Integer end, Integer gap, FacetSortingBehavior sorting) {
        super(field,sorting);
        this.start = start;
        this.end = end;
        this.gap = gap;
    }

    public List<RangeFacetOtherBehavior> getOtherBehaviors() {
        return otherBehaviors;
    }

    public void addOtherBehavior(RangeFacetOtherBehavior other) {
        this.otherBehaviors.add(other);
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Integer getGap() {
        return gap;
    }

    public boolean hasOther(){
        return this.otherBehaviors.size() > 0;
    }

    @Override
    public ModifiableSolrParams createSolrParams() {
        ModifiableSolrParams params = new ModifiableSolrParams();

        String fieldStart = String.format("f.%s.facet.range.", field);

        params.add(fieldStart +  "start", start.toString());
        params.add(fieldStart +  "end", end.toString());
        params.add(fieldStart +  "gap", gap.toString());
        params.add("facet.range", field.toString());
        params.add(String.format("f.%s.facet.sort", field.toString()), sorting.getValue());
        params.add(String.format("f.%s.facet.mincount", field), "" + minCount);

        if(hasOther()){
            for(RangeFacetOtherBehavior otherBehavior : otherBehaviors){
                params.add(fieldStart + "other", otherBehavior.getValue());
            }
        }

        return params;
    }

}
