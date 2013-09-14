package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
import nl.codebasesoftware.produx.search.result.FacetFieldView;
import nl.codebasesoftware.produx.search.result.NormalFacetFilterLink;
import nl.codebasesoftware.produx.search.result.RangeFacetFilterLink;
import nl.codebasesoftware.produx.search.result.SearchResult;
import nl.codebasesoftware.produx.util.Properties;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.RangeFacet;
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
@SuppressWarnings("unchecked")
public class QueryResponseToSearchResultConverter {

    private static final Logger LOG = Logger.getLogger(QueryResponseToSearchResultConverter.class);

    private ConversionService conversionService;
    private Properties properties;
    private QueryResponse queryResponse;
    private SearchCriteria criteria;
    private CategoryEntityDTO categoryEntityDTO;
    private SearchResult.Builder builder;

    @Autowired
    public QueryResponseToSearchResultConverter(ConversionService conversionService, Properties properties) {
        this.conversionService = conversionService;
        this.properties = properties;
    }

    public SearchResult convert(QueryResponse queryResponse, SearchCriteria criteria, CategoryEntityDTO categoryEntityDTO) {
        this.queryResponse = queryResponse;
        this.criteria = criteria;
        this.categoryEntityDTO = categoryEntityDTO;

        builder = new SearchResult.Builder();
        addCourses();
        addNormalFacetFields();
        addRangeFacetFields();
        builder.setTotalFound(queryResponse.getResults().getNumFound());
        builder.setResultsPerPage(properties.getSearchResultsPerPage());

        return builder.build();
    }

    private void addCourses() {
        final List<ListingCourseDTO> listingCourses = new ArrayList<>();
        for (SolrDocument solrDoc : queryResponse.getResults()) {
            listingCourses.add(conversionService.convert(solrDoc, ListingCourseDTO.class));
        }
        builder.setCourses(listingCourses);
    }

    private void addNormalFacetFields() {
        if (queryResponse.getFacetFields() != null) {
            for (FacetField facetField : queryResponse.getFacetFields()) {
                FacetFieldView facetFieldView = new FacetFieldView(facetField.getName());
                List<Count> values = facetField.getValues();
                for (Count value : values) {
                    facetFieldView.addValue(new NormalFacetFilterLink(facetField.getName(), value.getName(), value.getCount(), categoryEntityDTO, criteria));
                }
                builder.addFacetField(facetFieldView);
            }
        }
    }

    private void addRangeFacetFields() {
        if (queryResponse.getFacetRanges() != null) {
            for (RangeFacet rangeFacet : queryResponse.getFacetRanges()) {
                FacetFieldView facetFieldView = new FacetFieldView(rangeFacet.getName());
                facetFieldView.setProvideClearLink(true);
                List<RangeFacet.Count> counts = rangeFacet.getCounts();
                for (RangeFacet.Count value : counts) {
                    RangeFacetFilterLink rangeFacetFilterLink = new RangeFacetFilterLink(rangeFacet.getName(),
                            Integer.parseInt(value.getValue()), value.getCount(), (Integer)rangeFacet.getGap(), categoryEntityDTO, criteria);
                    facetFieldView.addValue(rangeFacetFilterLink);
                }
                builder.addFacetField(facetFieldView);
            }
        }
    }
}
