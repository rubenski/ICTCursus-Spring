package nl.codebasesoftware.produx.conversion;

import com.google.common.base.Joiner;
import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCompanyDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import org.apache.solr.common.SolrDocument;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

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
        dto.setName((String) solrDoc.getFieldValue("name"));
        dto.setCategory(CategoryEntityDTO.fromSolrValue((String) solrDoc.getFieldValue("category")));
        dto.setTagList(getTagsAsCSV(solrDoc));

        ListingCompanyDTO listingCompany = new ListingCompanyDTO();

        listingCompany.setId((Long)solrDoc.getFieldValue("company_id"));
        listingCompany.setName((String)solrDoc.getFieldValue("company_name"));
        listingCompany.setLogo(solrDoc.getFieldValue("company_has_logo") == 1);


        dto.setCompany(listingCompany);
        dto.setListDescription((String)solrDoc.getFieldValue("shortdescription"));
        return dto;
    }

    private String getTagsAsCSV(SolrDocument solrDoc){
        List tags = (List) solrDoc.getFieldValue("tag_names");
        if(tags == null) return "";
        return Joiner.on(", ").join(tags);
    }
}
