package nl.codebasesoftware.produx.search.solrquery.queryitems;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 5-8-13
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public enum FacetFieldRangeOtherBehaviour {

    AFTER("after"),
    BEFORE("before");

    private final String behaviour;

    private FacetFieldRangeOtherBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public String toString() {
        return behaviour;
    }
}
