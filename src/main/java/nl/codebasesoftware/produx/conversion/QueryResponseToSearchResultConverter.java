package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.search.NormalFacetFieldView;
import nl.codebasesoftware.produx.search.ProduxFacetField;
import nl.codebasesoftware.produx.search.RangeFacetFieldView;
import nl.codebasesoftware.produx.search.SearchResult;
import nl.codebasesoftware.produx.util.Properties;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 14-8-13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */

public class QueryResponseToSearchResultConverter {

    private static final Logger LOG = Logger.getLogger(QueryResponseToSearchResultConverter.class);

    private ConversionService conversionService;
    private Properties properties;

    @Autowired
    public QueryResponseToSearchResultConverter(ConversionService conversionService, Properties properties) {
        this.conversionService = conversionService;
        this.properties = properties;
    }

    public SearchResult convert(QueryResponse queryResponse, String baseUrl) {

        SearchResult.Builder builder = new SearchResult.Builder();
        addCourses(queryResponse, builder);
        addNormalFacetFields(queryResponse, builder, baseUrl);
        addRangeFacetFields(queryResponse, builder, baseUrl);
        builder.setTotalFound(queryResponse.getResults().getNumFound());
        builder.setResultsPerPage(properties.getSearchResultsPerPage());

        return builder.build();
    }

    private void addCourses(QueryResponse queryResponse, SearchResult.Builder builder) {
        List<ListingCourseDTO> listingCourses = new ArrayList<>();
        for (SolrDocument solrDoc : queryResponse.getResults()) {
            listingCourses.add(conversionService.convert(solrDoc, ListingCourseDTO.class));
        }
        builder.setCourses(listingCourses);
    }

    private void addNormalFacetFields(QueryResponse queryResponse, SearchResult.Builder builder, String baseUrl) {
        if (queryResponse.getFacetFields() != null) {
            for (FacetField facetField : queryResponse.getFacetFields()) {
                ProduxFacetField produxFacetField = new ProduxFacetField(facetField.getName());
                List<Count> values = facetField.getValues();
                for (Count value : values) {
                    produxFacetField.addValue(new NormalFacetFieldView(facetField.getName(), value.getName(), value.getCount(), baseUrl));
                }
                builder.addNormalFacetField(produxFacetField);
            }
        }
    }

    private void addRangeFacetFields(QueryResponse queryResponse, SearchResult.Builder builder, String baseUrl) {
        if (queryResponse.getFacetRanges() != null) {
            for (RangeFacet rangeFacet : queryResponse.getFacetRanges()) {
                ProduxFacetField produxFacetField = new ProduxFacetField(rangeFacet.getName());
                List<RangeFacet.Count> counts = rangeFacet.getCounts();
                for (RangeFacet.Count value : counts) {
                    RangeFacetFieldView rangeFacetFieldView = new RangeFacetFieldView(rangeFacet.getName(), Integer.parseInt(value.getValue()), value.getCount(), (Integer)rangeFacet.getGap(), baseUrl);
                    produxFacetField.addValue(rangeFacetFieldView);
                }

                builder.addNormalFacetField(produxFacetField);
            }
        }
    }
}
