package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
import nl.codebasesoftware.produx.search.result.*;
import nl.codebasesoftware.produx.util.Properties;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.springframework.core.convert.ConversionService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 14-8-13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
public class QueryResponseToSearchResultConverter {

    private static final Logger LOG = Logger.getLogger(QueryResponseToSearchResultConverter.class);

    private ConversionService conversionService;
    private Properties properties;
    private QueryResponse queryResponse;
    private SearchCriteria criteria;
    private CategoryEntityDTO categoryEntityDTO;

    @Autowired
    public QueryResponseToSearchResultConverter(ConversionService conversionService, Properties properties) {
        this.conversionService = conversionService;
        this.properties = properties;
    }

    public SearchResult convert(QueryResponse queryResponse, SearchCriteria criteria, CategoryEntityDTO categoryEntityDTO) {
        this.queryResponse = queryResponse;
        this.criteria = criteria;
        this.categoryEntityDTO = categoryEntityDTO;

        SearchResult.Builder searchResultBuilder = new SearchResult.Builder();
        addCourses(searchResultBuilder);
        addNormalFacetFields(searchResultBuilder);
        addRangeFacetFields(searchResultBuilder);
        searchResultBuilder.setTotalFound(queryResponse.getResults().getNumFound());
        searchResultBuilder.setResultsPerPage(properties.getSearchResultsPerPage());

        return searchResultBuilder.build();
    }

    private void addCourses(SearchResult.Builder searchResultBuilder) {
        for (SolrDocument solrDoc : queryResponse.getResults()) {
            searchResultBuilder.addCourse(conversionService.convert(solrDoc, ListingCourseDTO.class));
        }
    }

    private void addNormalFacetFields(SearchResult.Builder searchResultBuilder) {
        if (queryResponse.getFacetFields() != null) {
            for (FacetField facetField : queryResponse.getFacetFields()) {

                FacetFieldView facetFieldView = new FacetFieldView(facetField.getName());

                for (Count value : facetField.getValues()) {

                    NormalFacetFilterLink.Builder linkBuilder = new NormalFacetFilterLink.Builder();

                    linkBuilder.setCategory(categoryEntityDTO)
                            .setCount(value.getCount())
                            .setCriteria(criteria)
                            .setField(facetField.getName())
                            .setValue(value.getName());

                    facetFieldView.addFilterLink(linkBuilder.build());
                }
                searchResultBuilder.addFacetField(facetFieldView);

            }
        }
    }

    private void addRangeFacetFields(SearchResult.Builder searchResultBuilder) {
        if (queryResponse.getFacetRanges() != null) {
            for (RangeFacet rangeFacet : queryResponse.getFacetRanges()) {

                FacetFieldView facetFieldView = new FacetFieldView(rangeFacet.getName());
                List<RangeFacet.Count> counts = rangeFacet.getCounts();

                RangeFilterLinkStrategy labelStrategy = null;
                if(rangeFacet.getName().equals("price")){
                    labelStrategy = new CentPriceRangeStrategy();
                }

                for (RangeFacet.Count value : counts) {


                    RangeFacetFilterLink.Builder builder = new RangeFacetFilterLink.Builder();

                    builder.setCount(value.getCount())
                            .setCategory(categoryEntityDTO)
                            .setCriteria(criteria)
                            .setField(rangeFacet.getName())
                            .setValue(value.getValue())
                            .setGap(rangeFacet.getGap())
                            .setRangeLinkStrategy(labelStrategy);



                    facetFieldView.addFilterLink(builder.build());
                }
                searchResultBuilder.addFacetField(facetFieldView);


            }
        }
    }
}
