package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class RangeFacet {

    String field;
    Integer start;
    Integer end;
    Integer gap;
    String other;

    public RangeFacet(String field, Integer start, Integer end, Integer gap) {
        this.start = start;
        this.end = end;
        this.gap = gap;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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
        return this.other != null;
    }
}
