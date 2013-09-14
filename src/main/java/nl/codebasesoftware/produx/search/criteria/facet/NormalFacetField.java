package nl.codebasesoftware.produx.search.criteria.facet;

import org.apache.solr.common.params.ModifiableSolrParams;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-9-13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class NormalFacetField extends FacetField {

    public NormalFacetField(String field, FacetSortingBehavior sorting) {
        super(field, sorting);
    }

    public FacetSortingBehavior getSorting() {
        return sorting;
    }

    @Override
    public ModifiableSolrParams createSolrParams() {
        ModifiableSolrParams params = new ModifiableSolrParams();
        // &facet.field={!ex=dt}doctype

        StringBuilder excludedFiltersPrefix = new StringBuilder();
        for (String excludedFilter : excludedFilters) {
            excludedFiltersPrefix.append("{!ex=").append(excludedFilter).append("}");
        }

        params.add("facet.field", String.format("%s%s", excludedFiltersPrefix, field));
        params.add(String.format("f.%s.facet.sort", field), sorting.getValue());
        params.add(String.format("f.%s.facet.mincount", field), "" + minCount);
        return params;
    }
}
