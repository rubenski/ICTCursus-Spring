package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-9-13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public abstract class Facet {

    protected FacetSortingBehavior sorting;
    protected String field;
    protected int minCount = 0;

    protected Facet(String field,FacetSortingBehavior sorting) {
        this.sorting = sorting;
        this.field = field;
    }

    public FacetSortingBehavior getSorting() {
        return sorting;
    }

    public void setSorting(FacetSortingBehavior sorting) {
        this.sorting = sorting;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }
}
