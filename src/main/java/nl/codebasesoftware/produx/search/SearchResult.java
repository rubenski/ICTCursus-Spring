package nl.codebasesoftware.produx.search;

import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 13-8-13
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public class SearchResult {

    private Collection<ListingCourseDTO> courses = new ArrayList<>();
    private Collection<ProduxFacetField> produxFacetFields = new ArrayList<>();
    private long totalFound;
    private Integer resultsPerPage;

    private SearchResult(Builder builder){
        courses = builder.courses;
        produxFacetFields = builder.produxFacetFields;
        totalFound = builder.totalFound;
        resultsPerPage = builder.resultsPerPage;
    }

    public static class Builder {

        private Collection<ListingCourseDTO> courses = new ArrayList<>();
        private Collection<ProduxFacetField> produxFacetFields = new ArrayList<>();
        private long totalFound;
        private Integer resultsPerPage;

        public Builder setCourses(Collection<ListingCourseDTO> courses){
            this.courses = courses;
            return this;
        }

        public Builder addFacetField(ProduxFacetField produxFacetField){
            this.produxFacetFields.add(produxFacetField);
            return this;
        }

        public SearchResult build(){
            return new SearchResult(this);
        }

        public Builder setTotalFound(long totalFound) {
            this.totalFound = totalFound;
            return this;
        }

        public Builder setProduxFacetFields(Collection<ProduxFacetField> produxFacetFields) {
            this.produxFacetFields = produxFacetFields;
            return this;
        }

        public Builder setResultsPerPage(int resultsPerPage){
            this.resultsPerPage = resultsPerPage;
            return this;
        }
    }

    public Collection<ListingCourseDTO> getCourses() {
        return courses;
    }

    public Collection<ProduxFacetField> getProduxFacetFields() {
        return produxFacetFields;
    }

    public long getTotalFound() {
        return totalFound;
    }

    public Integer getResultsPerPage() {
        return resultsPerPage;
    }

    public int getNumberOfResultPages(){
        return (int) Math.ceil(totalFound / resultsPerPage);
    }
}
