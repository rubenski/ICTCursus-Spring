package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.search.*;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

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
        params.add(FilterCreator.createFilters(criteria));
        params.add(FacetCreator.createFacets(criteria));
        params.add("start", "" + criteria.getStart());
        params.add("rows", "" + criteria.getRows());
        return params;
    }
}
