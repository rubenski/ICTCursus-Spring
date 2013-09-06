package nl.codebasesoftware.produx.search;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 9-8-13
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public class SearchCriteria {

    private String query = "*:*";
    private Integer start;
    private Integer rows;
    private List<FacetField> facetFields;
    private List<Filter> filters;

    public String getQuery() {
        return query;
    }

    public List<FacetField> getFacetFields() {
        return facetFields;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getRows() {
        return rows;
    }

    private SearchCriteria(Builder builder){
        rows = builder.rows;
        start = builder.start;
        query = builder.query;
        facetFields = builder.facetFields;
        filters = builder.filters;
    }

    public static class Builder {

        private String query = "*:*";
        private List<FacetField> facetFields = new ArrayList<>();
        private List<Filter> filters = new ArrayList<>();
        private Integer start;
        private Integer rows;


        public Builder setQuery(String query) {
            this.query = query;
            return this;
        }

        public Builder addFacetField(FacetField facet){
            this.facetFields.add(facet);
            return this;
        }

        public Builder addFilter(Filter filter){
            this.filters.add(filter);
            return this;
        }

        public Builder setStart(int offset) {
            this.start = offset;
            return this;
        }

        public Builder setRows(int rows) {
            this.rows = rows;
            return this;
        }

        public SearchCriteria build(){
            return new SearchCriteria(this);
        }
    }
}
