package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.search.solrquery.SolrQuery;
import nl.codebasesoftware.produx.search.solrquery.queryitems.FacetFieldParameter;
import nl.codebasesoftware.produx.search.solrquery.queryitems.FacetFieldRangeParameter;
import nl.codebasesoftware.produx.search.solrquery.queryitems.SearchPhraseParameter;
import nl.codebasesoftware.produx.service.CourseService;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: rvanloen
 * Date: 30-1-13
 * Time: 15:03
 */
@Controller
public class AdminSolrController {

    /*
        HttpSolrServer is thread-safe and if you are using the following constructor,
        you *MUST* re-use the same instance for all requests.  If instances are created on
        the fly, it can cause a connection leak. The recommended practice is to keep a
        static instance of HttpSolrServer per solr server url and share it for all requests.
        See https://issues.apache.org/jira/browse/SOLR-861 for more details

        In this application the Solr server is defined as a singleton scoped Spring bean, which also works.
    */
    private HttpSolrServer solrServer;

    private ConversionService conversionService;
    private CourseService courseService;

    private static Logger LOG = Logger.getLogger(AdminSolrController.class);

    @Autowired
    public AdminSolrController(CourseService courseService, ConversionService conversionService, HttpSolrServer solrServer) {
        this.courseService = courseService;
        this.conversionService = conversionService;
        this.solrServer = solrServer;
    }

    @RequestMapping(value = "/admin/updatesolr")
    public String updateSolr(Model model) {

        List<SolrInputDocument> solrDocuments = new ArrayList<SolrInputDocument>();
        List<Course> indexableCourses = courseService.findIndexableCourses();

        for (Course indexableCourse : indexableCourses) {
            SolrInputDocument solrInputDocument = conversionService.convert(indexableCourse, SolrInputDocument.class);
            solrDocuments.add(solrInputDocument);
        }

        try {
            solrServer.add(solrDocuments);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("mainContent", "content/solr");

        ModifiableSolrParams solrParams = new ModifiableSolrParams();


        // Fetch some data from Solr just for testing
        // This works directly on the server: q=cursus&facet=on&facet.queryitems=price
        solrParams.set("q", "cursus");
        solrParams.set("facet", true);
        solrParams.set("facet.queryitems", "price");
        solrParams.set("facet.range", "price");
        solrParams.set("f.price.facet.range.start", 0);
        solrParams.set("f.price.facet.range.end", 100000);
        solrParams.set("f.price.facet.range.gap", 10000);


        /*
        SolrQuery query = new SolrQuery.Builder()
                .setSearchPhrase(new SearchPhraseParameter("cursus"))
                .addFacetField(new FacetFieldParameter("price"))
                .addFacetField(new FacetFieldParameter("je moeder"))
                .addFacetFieldRange(new FacetFieldRangeParameter("price", 10000L, 100000L, 10000L))
                .build();
        */

        QueryResponse queryResponse = null;
        // http://local.tomcat:8983/solr/select?q=cursus&facet=on&f.price.facet.range.start=0&f.price.facet.range.end=10000&f.price.facet.range.gap=1000&facet.range.other=after&wt=xml
        try {
            // look here for query examples: http://wiki.constellio.com/index.php/Solrj_example`
            queryResponse = solrServer.query(solrParams);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }


        for (RangeFacet rangeFacet : queryResponse.getFacetRanges()) {
            String rangeFacetName = rangeFacet.getName();
            LOG.debug(rangeFacetName);

            List<RangeFacet.Count> counts = rangeFacet.getCounts();

            for (RangeFacet.Count count : counts) {
                LOG.debug(count.getValue() + " : " + count.getCount());
            }

        }

        SolrDocumentList results = queryResponse.getResults();
        model.addAttribute("result", results);

        return "adminMain";

    }
}
