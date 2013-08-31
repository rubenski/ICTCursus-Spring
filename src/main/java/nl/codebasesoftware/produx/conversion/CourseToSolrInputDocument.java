package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 30-1-13
 * Time: 16:53
 */
public class CourseToSolrInputDocument implements Converter<CourseEntityDTO, SolrInputDocument> {



    @Override
    public SolrInputDocument convert(CourseEntityDTO course) {

        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("course_id", course.getId());
        solrInputDocument.addField("name", course.getName());
        solrInputDocument.addField("longdescription", course.getLongDescription());
        solrInputDocument.addField("shortdescription", course.getListDescription());
        solrInputDocument.addField("price", course.getPrice());
        solrInputDocument.addField("category", course.getCategory().getSolrValue());
        solrInputDocument.addField("company_name", course.getCompany().getName());
        solrInputDocument.addField("company_id", course.getCompany().getId());
        solrInputDocument.addField("company_has_logo", course.getCompany().hasLogo() ? 1 : 0);
        solrInputDocument.addField("regions", course.getRegionNames());
        solrInputDocument.addField("region_ids", course.getRegionIds());
        solrInputDocument.addField("tags", course.getTagNames());
        solrInputDocument.addField("tag_ids", course.getTagIds());
        solrInputDocument.addField("certificate", course.isCertificate());

        return solrInputDocument;
    }
}