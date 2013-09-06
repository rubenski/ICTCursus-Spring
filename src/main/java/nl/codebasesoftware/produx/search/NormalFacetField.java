package nl.codebasesoftware.produx.search;

import org.apache.solr.common.params.ModifiableSolrParams;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-9-13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class NormalFacetField extends FacetField {

    protected NormalFacetField(String field, FacetSortingBehavior sorting) {
        super(field, sorting);
    }

    public FacetSortingBehavior getSorting() {
        return sorting;
    }

    @Override
    public ModifiableSolrParams createSolrParams() {
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.add("facet.field", field);
        params.add(String.format("f.%s.facet.sort", field), sorting.getValue());
        params.add(String.format("f.%s.facet.mincount", field), "" + minCount);
        return params;
    }
}
