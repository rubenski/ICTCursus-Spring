package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.search.FacetField;
import nl.codebasesoftware.produx.search.Filter;
import nl.codebasesoftware.produx.search.SearchCriteria;
import nl.codebasesoftware.produx.search.SolrParameter;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
public class SearchCriteriaToSolrParams implements Converter<SearchCriteria, SolrQuery> {

    public SolrQuery convert(SearchCriteria criteria) {

        SolrQuery qq = new SolrQuery();

        ModifiableSolrParams params = new ModifiableSolrParams();

        params.add("q", criteria.getQuery());
        params.add("start", "" + criteria.getStart());
        params.add("rows", "" + criteria.getRows());
        params.add("facet", "on");


        for (FacetField facet : criteria.getFacetFields()) {
            qq.add(facet.createSolrParams());
        }

        String filters = "";
        int number = criteria.getFilters().size();
        for (int i = 0; i < number; i++) {

            Filter filter = criteria.getFilters().get(i);
            qq.addFilterQuery(filter.createSolrParams().get("fq"));
        }

        qq.add(params);
        return qq;
    }
}
