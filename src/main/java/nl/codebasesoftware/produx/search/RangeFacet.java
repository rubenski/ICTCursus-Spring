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
public class RangeFacet {

    private String field;
    private Integer start;
    private Integer end;
    private Integer gap;
    private List<RangeFacetOtherBehavior> otherBehaviors = new ArrayList<>();
    private Integer minResultCount;

    public RangeFacet(String field, Integer start, Integer end, Integer gap) {
        this.start = start;
        this.end = end;
        this.gap = gap;
        this.field = field;
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

    public void setMinResultCount(int min){
        this.minResultCount = min;
    }

    public Integer getMinResultCount() {
        return minResultCount;
    }
}
