package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.dto.entity.ListingCourseDTO;
import nl.codebasesoftware.produx.search.ProduxFacetField;
import nl.codebasesoftware.produx.search.SearchResult;
import nl.codebasesoftware.produx.service.CourseService;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.solr.client.solrj.response.FacetField.Count;

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

    private CourseService courseService;

    @Autowired
    public QueryResponseToSearchResultConverter(CourseService courseService) {
        this.courseService = courseService;
    }

    public SearchResult convert(QueryResponse queryResponse) {

        SearchResult.Builder builder = new SearchResult.Builder();

        addCourses(queryResponse, builder);
        addNormalFacetFields(queryResponse, builder);
        addRangeFacetFields(queryResponse, builder);

        return builder.build();
    }

    private void addCourses(QueryResponse queryResponse, SearchResult.Builder builder) {
        List<Long> ids = new ArrayList<>();
        for (SolrDocument course : queryResponse.getResults()) {
            ids.add((Long) course.getFieldValue("course_id"));
        }

        List<ListingCourseDTO> listingCourses = courseService.findForListing(ids);
        builder.setCourses(listingCourses);
    }

    private void addNormalFacetFields(QueryResponse queryResponse, SearchResult.Builder builder) {
        if (queryResponse.getFacetFields() != null) {
            for (FacetField facetField : queryResponse.getFacetFields()) {

                ProduxFacetField produxFacetField = new ProduxFacetField();
                produxFacetField.setFieldName(facetField.getName());
                List<Count> values = facetField.getValues();
                for (Count value : values) {
                    produxFacetField.addValue(value.getName(), value.getCount());

                }

                builder.addNormalFacetField(produxFacetField);
            }
        }
    }

    private void addRangeFacetFields(QueryResponse queryResponse, SearchResult.Builder builder) {
        if (queryResponse.getFacetRanges() != null) {
            for (RangeFacet rangeFacet : queryResponse.getFacetRanges()) {
                ProduxFacetField produxFacetField = new ProduxFacetField();
                produxFacetField.setFieldName(rangeFacet.getName());
                List<Count> values = rangeFacet.getCounts();
                for (Count value : values) {
                    produxFacetField.addValue(value.getName(), value.getCount());
                }

                builder.addNormalFacetField(produxFacetField);
            }
        }
    }
}
