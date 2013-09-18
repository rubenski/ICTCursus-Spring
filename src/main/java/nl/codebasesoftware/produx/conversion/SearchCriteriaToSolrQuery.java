package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
import nl.codebasesoftware.produx.search.criteria.SolrParameters;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.core.convert.converter.Converter;

import java.util.Iterator;
import java.util.List;

/**
 * User: rvanloen
 * Date: 10-8-13
 * Time: 13:28
 *
 * Turns my pretty Solr abstraction into Solrj's less pretty (but still useful) Solr abstraction
 */
public class SearchCriteriaToSolrQuery implements Converter<SearchCriteria, SolrQuery> {

    public SolrQuery convert(SearchCriteria criteria) {

        SolrQuery solrQuery = new SolrQuery();

        solrQuery.add("q", criteria.getQuery());
        solrQuery.add("start", "" + criteria.getStart());
        solrQuery.add("rows", "" + criteria.getRows());
        solrQuery.add("facet", "on");

        addParams(criteria.getFacetFields(), solrQuery);
        addParams(criteria.getFilters(), solrQuery);

        return solrQuery;
    }

    private void addParams(List<? extends SolrParameters> parameters, SolrQuery solrQuery) {
        for (SolrParameters parameter : parameters) {
            ModifiableSolrParams solrParams = parameter.createSolrParams();
            Iterator<String> iterator = solrParams.getParameterNamesIterator();
            while (iterator.hasNext()) {
                String param = iterator.next();
                String value = solrParams.get(param);
                solrQuery.add(param, value);
            }
        }
    }
}

