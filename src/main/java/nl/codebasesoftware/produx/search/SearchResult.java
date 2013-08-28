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

    private SearchResult(Builder builder){
        courses = builder.courses;

    }

    public static class Builder {

        private Collection<ListingCourseDTO> courses = new ArrayList<>();
        private Collection<ProduxFacetField> produxFacetFields = new ArrayList<>();

        public Builder setCourses(Collection<ListingCourseDTO> courses){
            this.courses = courses;
            return this;
        }

        public Builder addNormalFacetField(ProduxFacetField produxFacetField){
            this.produxFacetFields.add(produxFacetField);
            return this;
        }

        public SearchResult build(){
            return new SearchResult(this);
        }
    }

    public Collection<ListingCourseDTO> getCourses() {
        return courses;
    }

    public Collection<ProduxFacetField> getProduxFacetFields() {
        return produxFacetFields;
    }
}
