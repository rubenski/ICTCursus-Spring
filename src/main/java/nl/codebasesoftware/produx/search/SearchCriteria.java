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

    private List<CategoryEntityDTO> categories = new ArrayList<>();
    private String query;
    private List<PriceRangeFilter> priceRanges = new ArrayList<>();
    private List<Long> regionIds = new ArrayList<>();
    private List<Long> tagIds = new ArrayList<>();
    private List<String> facetFields = new ArrayList<>();
    private List<RangeFacet> rangeFacets = new ArrayList<>();

    public List<CategoryEntityDTO> getCategories() {
        return categories;
    }

    public String getQuery() {
        return query;
    }

    public List<PriceRangeFilter> getPriceRanges() {
        return priceRanges;
    }

    public List<Long> getRegionIds() {
        return regionIds;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public List<String> getFacetFields() {
        return facetFields;
    }

    public List<RangeFacet> getRangeFacets() {
        return rangeFacets;
    }

    private SearchCriteria(Builder builder){
        categories = builder.categories;
        query = builder.query;
        priceRanges = builder.priceRanges;
        regionIds = builder.regions;
        tagIds = builder.tags;
        facetFields = builder.facetFields;
        rangeFacets = builder.rangeFacets;
    }

    public static class Builder {

        private List<CategoryEntityDTO> categories = new ArrayList<>();
        private String query = "*:*";
        private List<PriceRangeFilter> priceRanges = new ArrayList<>();
        private List<Long> regions = new ArrayList<>();
        private List<Long> tags = new ArrayList<>();
        private List<String> facetFields = new ArrayList<>();
        private List<RangeFacet> rangeFacets = new ArrayList<>();


        public Builder setQuery(String query) {
            this.query = query;
            return this;
        }

        public Builder addCategory(CategoryEntityDTO category){
            this.categories.add(category);
            return this;
        }

        public Builder addPriceRange(PriceRangeFilter range){
            priceRanges.add(range);
            return this;
        }

        public Builder addRegion(long regionId){
            this.regions.add(regionId);
            return this;
        }

        public Builder addTag(long tagId){
            this.tags.add(tagId);
            return this;
        }

        public Builder addFacetField(String fieldName){
            this.facetFields.add(fieldName);
            return this;
        }

        public Builder addRangeFacetField(RangeFacet rangeFacet){
            this.rangeFacets.add(rangeFacet);
            return this;
        }

        public SearchCriteria build(){
            // TODO: equals if values in builder are retained across usages
            return new SearchCriteria(this);
        }
    }
}
