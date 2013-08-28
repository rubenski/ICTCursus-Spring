package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCompanyDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import org.apache.solr.common.SolrDocument;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 28-8-13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class SolrDocumentToListingCourse implements Converter<SolrDocument, ListingCourseDTO> {

    @Override
    public ListingCourseDTO convert(SolrDocument solrDoc) {
        ListingCourseDTO dto = new ListingCourseDTO();

        dto.setId((Long)solrDoc.getFieldValue("course_id"));
        dto.setName((String)solrDoc.getFieldValue("name"));
        dto.setCategory(CategoryEntityDTO.fromSolrValue((String) solrDoc.getFieldValue("category")));
        ListingCompanyDTO listingCompany = new ListingCompanyDTO((Long)solrDoc.getFieldValue("company_id"),
                (String)solrDoc.getFieldValue("company_name"));
        dto.setCompany(listingCompany);
        dto.setListDescription((String)solrDoc.getFieldValue("shortdescription"));
        return dto;
    }
}
