package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.service.CourseService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
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
    */
    private static String SOLR_URL = "http://local.tomcat:8983/solr";
    private static SolrServer server = new HttpSolrServer(SOLR_URL);
    private ConversionService conversionService;
    private CourseService courseService;

    @Autowired
    public AdminSolrController(CourseService courseService, ConversionService conversionService) {
        this.courseService = courseService;
        this.conversionService = conversionService;
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
            server.add(solrDocuments);
            server.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("mainContent", "content/managementSolr");

        ModifiableSolrParams solrParams = new ModifiableSolrParams();


        // Fetch some data from Solr just for testing
        String query ="cursus";
		solrParams.set("q", query);

        String result = "";

        try {
            // look here for query examples: http://wiki.constellio.com/index.php/Solrj_example`
            QueryResponse queryResponse = server.query(solrParams);

            for (SolrDocument solrDocument : queryResponse.getResults()) {
                result += String.format("%s<br>", solrDocument.getFieldValue("id"));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        model.addAttribute("result", result);

        return "adminMain";

    }
}
