package nl.codebasesoftware.produx.search.criteria.facet;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 2-9-13
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
public enum RangeFacetOtherBehavior {

    /**
     *
     * before - all records with field filterLinks lower then lower bound of the first range
       after - all records with field filterLinks greater then the upper bound of the last range
       between - all records with field filterLinks between the start and end bounds of all ranges
       none - compute none of this information
       all - shortcut for before, between, and after
     */

    BEFORE("before"),
    AFTER("after"),
    BETWEEN("between"),
    NONE("none"),
    ALL("all");

    private final String value;

    private RangeFacetOtherBehavior(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
