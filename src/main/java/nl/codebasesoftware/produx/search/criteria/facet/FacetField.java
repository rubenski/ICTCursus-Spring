package nl.codebasesoftware.produx.search.criteria.facet;

import nl.codebasesoftware.produx.search.criteria.SolrParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public abstract class FacetField implements SolrParameter {

    protected String field;
    protected int minCount;
    protected FacetSortingBehavior sorting;
    protected List<String> excludedFilters = new ArrayList<>();

    protected FacetField(String field, FacetSortingBehavior sorting) {
        this.field = field;
        this.sorting = sorting;
    }

    public String getField() {
        return field;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount){
        this.minCount = minCount;
    }

    public FacetSortingBehavior getSorting() {
        return sorting;
    }

    public void addExcludedFilter(String exclude){
        excludedFilters.add(exclude);
    }
}
