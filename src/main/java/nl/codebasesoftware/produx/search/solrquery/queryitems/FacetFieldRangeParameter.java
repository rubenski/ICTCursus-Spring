package nl.codebasesoftware.produx.search.solrquery.queryitems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 31-7-13
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */
public class FacetFieldRangeParameter implements SolrQueryParameters {

    private final String field;
    private final Long start;
    private final Long end;
    private final Long gap;
    private FacetFieldRangeOtherBehaviour other;

    public FacetFieldRangeParameter(String field, Long start, Long end, Long gap) {
        this.field = field;
        this.start = start;
        this.end = end;
        this.gap = gap;
    }

    public void setOther(FacetFieldRangeOtherBehaviour other) {
        this.other = other;
    }

    @Override
    public List<String> toQueryParameters() {

        List<String> params = new ArrayList<String>();
        String facetRangeParameterBegin = String.format("f.%s.facet.range.", field);

        params.add(String.format("%sstart=%s", facetRangeParameterBegin, start));
        params.add(String.format("%send=%s", facetRangeParameterBegin, end));
        params.add(String.format("%sgap=%s", facetRangeParameterBegin, gap));

        if (other != null) {
            // TODO: equals if this works, since there is no mention of the queryitems name in facet.range.other. How could we do this twice in a query?
            params.add(String.format("facet.range.other=%s", other));
        }

        return params;
    }
}
