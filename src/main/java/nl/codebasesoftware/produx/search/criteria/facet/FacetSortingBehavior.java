package nl.codebasesoftware.produx.search.criteria.facet;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-9-13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public enum FacetSortingBehavior {

    NATURAL_ORDER("index"),
    COUNT("count");

    private final String value;

    private FacetSortingBehavior(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
