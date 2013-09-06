package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.search.FacetField;
import nl.codebasesoftware.produx.search.Filter;
import nl.codebasesoftware.produx.search.SearchCriteria;
import nl.codebasesoftware.produx.search.SolrParameter;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
public class SearchCriteriaToSolrParams implements Converter<SearchCriteria, ModifiableSolrParams> {

    public ModifiableSolrParams convert(SearchCriteria criteria) {

        ModifiableSolrParams params = new ModifiableSolrParams();

        params.add("q", criteria.getQuery());


        for (FacetField facet : criteria.getFacetFields()) {
            params.add(facet.createSolrParams());
        }

        for (Filter filter : criteria.getFilters()) {
            params.add(filter.createSolrParams());
        }

        params.add("start", "" + criteria.getStart());
        params.add("rows", "" + criteria.getRows());
        params.add("facet", "on");
        return params;
    }
}
