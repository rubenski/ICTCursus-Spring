package nl.codebasesoftware.produx.search.result;

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
    private Collection<FacetFieldView> facetFieldViews = new ArrayList<>();
    private long totalFound;
    private Integer resultsPerPage;

    private SearchResult(Builder builder){
        courses = builder.courses;
        facetFieldViews = builder.facetFieldViews;
        totalFound = builder.totalFound;
        resultsPerPage = builder.resultsPerPage;
    }

    public static class Builder {

        private Collection<ListingCourseDTO> courses = new ArrayList<>();
        private Collection<FacetFieldView> facetFieldViews = new ArrayList<>();
        private long totalFound;
        private Integer resultsPerPage;

        public Builder addCourse(ListingCourseDTO course){
            this.courses.add(course);
            return this;
        }

        public Builder addFacetField(FacetFieldView facetFieldView){
            this.facetFieldViews.add(facetFieldView);
            return this;
        }

        public SearchResult build(){
            return new SearchResult(this);
        }

        public Builder setTotalFound(long totalFound) {
            this.totalFound = totalFound;
            return this;
        }

        public Builder setFacetFieldViews(Collection<FacetFieldView> facetFieldViews) {
            this.facetFieldViews = facetFieldViews;
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

    public Collection<FacetFieldView> getFacetFieldViews() {
        return facetFieldViews;
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
