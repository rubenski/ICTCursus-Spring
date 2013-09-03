package nl.codebasesoftware.produx.search;

import nl.codebasesoftware.produx.search.solrquery.RangeFacetOtherBehavior;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class RangeFacet extends Facet {

    private Integer start;
    private Integer end;
    private Integer gap;
    private List<RangeFacetOtherBehavior> otherBehaviors = new ArrayList<>();

    public RangeFacet(String field, Integer start, Integer end, Integer gap, FacetSortingBehavior sorting) {
        super(field,sorting);
        this.start = start;
        this.end = end;
        this.gap = gap;
    }

    public String getField() {
        return field;
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

}
