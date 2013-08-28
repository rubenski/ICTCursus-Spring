package nl.codebasesoftware.produx.search.solrquery;

import nl.codebasesoftware.produx.search.solrquery.exception.InvalidSolrQueryParametersException;
import nl.codebasesoftware.produx.search.solrquery.filter.FacetFilter;
import nl.codebasesoftware.produx.search.solrquery.queryitems.FacetFieldParameter;
import nl.codebasesoftware.produx.search.solrquery.queryitems.FacetFieldRangeParameter;
import nl.codebasesoftware.produx.search.solrquery.queryitems.SearchPhraseParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 12:55
 */

public class SolrQuery {

    String query;

    private SolrQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public static class Builder {

        // Optional parameters
        private SearchPhraseParameter searchPhrase;
        private List<FacetFieldParameter> facetFields = new ArrayList<FacetFieldParameter>();
        private List<FacetFieldRangeParameter> facetFieldRanges = new ArrayList<FacetFieldRangeParameter>();
        private List<FacetFilter> facetFilters = new ArrayList<FacetFilter>();
        private boolean facetsOn;


        public Builder setSearchPhrase(SearchPhraseParameter searchPhrase) {
            this.searchPhrase = searchPhrase;
            return this;
        }

        public Builder addFacetField(FacetFieldParameter field) {
            facetFields.add(field);
            return this;
        }

        public Builder addFacetFieldRange(FacetFieldRangeParameter range) {
            facetFieldRanges.add(range);
            return this;
        }

        public Builder addFacetFilter(FacetFilter filter) {
            facetFilters.add(filter);
            return this;
        }

        public boolean facetsOn() {
            return facetFields.size() > 0 || facetFieldRanges.size() > 0;
        }

        public SolrQuery build() {

            try {
                new SolrQueryBuilderValidator().validate(this);
            } catch (InvalidSolrQueryParametersException e) {
                e.printStackTrace();
            }

            // http://local.tomcat:8983/solr/select?q=cursus&facet=on&f.price.facet.range.start=0&f.price.facet.range.end=10000&f.price.facet.range.gap=1000&facet.range.other=after&wt=xml

            SolrQueryStringBuilder queryBuilder = new SolrQueryStringBuilder();

            queryBuilder.appendSomething("select");

            if (searchPhrase != null) {
                queryBuilder.appendParameters(searchPhrase.toQueryParameters());
            }

            if (facetsOn) {
                queryBuilder.appendSomething("&facet=on");
            }

            for (FacetFieldParameter facetField : facetFields) {
                queryBuilder.appendParameters(facetField.toQueryParameters());
            }

            for (FacetFieldRangeParameter facetFieldRange : facetFieldRanges) {
                queryBuilder.appendParameters(facetFieldRange.toQueryParameters());
            }

            return new SolrQuery(queryBuilder.toString());
        }

        private class SolrQueryBuilderValidator {

            public SolrQueryValidationResult validate(SolrQuery.Builder queryBuilder) throws InvalidSolrQueryParametersException {
                SolrQueryValidationResult result = new SolrQueryValidationResult();
                return result;
            }
        }


    }
}
