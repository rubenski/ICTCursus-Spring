package nl.codebasesoftware.produx.search.criteria.filter;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public enum FilterConditions {

    AND("AND"),
    OR("OR");

    private final String condition;

    private FilterConditions(String condition) {
        this.condition = condition;
    }

    public String getValue() {
        return condition;
    }
}
