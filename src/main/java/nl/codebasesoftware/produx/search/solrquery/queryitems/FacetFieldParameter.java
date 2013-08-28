package nl.codebasesoftware.produx.search.solrquery.queryitems;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 31-7-13
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class FacetFieldParameter implements SolrQueryParameters {

    private String field;

    public FacetFieldParameter(String field) {
        this.field = field;
    }

    @Override
    public List<String> toQueryParameters() {
        return Arrays.asList(String.format("facet.field=%s", field));
    }
}

