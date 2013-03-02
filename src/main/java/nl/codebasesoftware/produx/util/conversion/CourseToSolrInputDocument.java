package nl.codebasesoftware.produx.util.conversion;

import nl.codebasesoftware.produx.domain.Course;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 30-1-13
 * Time: 16:53
 */
public class CourseToSolrInputDocument implements Converter<Course, SolrInputDocument> {


    @Override
    public SolrInputDocument convert(Course course) {

        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", course.getId());
        solrInputDocument.addField("name", course.getName());
        solrInputDocument.addField("longdescription", course.getLongDescription());
        solrInputDocument.addField("shortdescription", course.getShortDescription());
        // TODO: remove certificateText field from schema.xml. This field had been removed
        solrInputDocument.addField("price", course.getPrice());
        solrInputDocument.addField("category", course.getCategory().getName());
        solrInputDocument.addField("company", course.getCompany().getName());
        solrInputDocument.addField("regions", course.getRegionNames());
        solrInputDocument.addField("tags", course.getTagNames());
        solrInputDocument.addField("certificate", course.isCertificate());

        return solrInputDocument;
    }
}