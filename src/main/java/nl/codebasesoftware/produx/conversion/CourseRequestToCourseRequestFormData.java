package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 14:59
 */
public class CourseRequestToCourseRequestFormData implements Converter<CourseRequest, CourseRequestFormData> {

    @Override
    public CourseRequestFormData convert(CourseRequest courseRequest) {
        CourseRequestFormData formData = new CourseRequestFormData();
        formData.setCourseId(courseRequest.getCourse().getId());
        formData.setEmail(courseRequest.getEmail());
        formData.setMessage(courseRequest.getMessage());
        formData.setCourseName(courseRequest.getCourseName());
        formData.setRequesterName(courseRequest.getRequesterName());
        formData.setNumberOfParticipants(courseRequest.getNumberOfParticipants());
        formData.setPrefix(courseRequest.getPrefix());
        formData.setInvalid(courseRequest.isInvalid());
        formData.setId(courseRequest.getId());
        return formData;
    }
}
