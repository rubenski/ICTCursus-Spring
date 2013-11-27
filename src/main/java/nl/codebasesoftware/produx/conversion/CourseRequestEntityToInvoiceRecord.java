package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.InvoiceRecord;
import nl.codebasesoftware.produx.domain.InvoiceRecordType;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.Prefixes;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * User: rvanloen
 * Date: 4-11-13
 * Time: 20:24
 */
public class CourseRequestEntityToInvoiceRecord implements Converter<CourseRequestEntityDTO, InvoiceRecord> {

    @Override
    public InvoiceRecord convert(CourseRequestEntityDTO courseRequest) {
        InvoiceRecord record = new InvoiceRecord();
        record.setPriceInCents(courseRequest.getInvoicePriceInCents());
        record.setSourceRecordCreated(courseRequest.getCreated());
        record.setDescription(courseRequest.getInvoiceDescription());
        record.setType(InvoiceRecordType.COURSE_REQUEST);


        Map<String, String> fields = new HashMap<>();
        fields.put("id", courseRequest.getId().toString());
        fields.put("email", courseRequest.getEmail());
        fields.put("courseName", courseRequest.getCourseName());
        fields.put("message", courseRequest.getMessage());
        fields.put("phone", courseRequest.getPhone());
        fields.put("requesterName", courseRequest.getRequesterName());
        fields.put("numberOfParticipants", "" + courseRequest.getNumberOfParticipants());
        fields.put("prefix", courseRequest.getPrefix() == 0 ? Prefixes.SIR.toString() : Prefixes.MADAM.toString());
        fields.put("courseId", courseRequest.getCourse().getId().toString());

        record.setSourceFields(fields);

        return record;
    }
}
