package nl.codebasesoftware.produx.search.solrquery.filter;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 31-7-13
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */
public class FacetFilter {

    String field;
    String value;

    public FacetFilter(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
