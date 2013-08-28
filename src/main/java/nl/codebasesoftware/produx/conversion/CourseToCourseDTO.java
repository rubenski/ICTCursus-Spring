package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
public class CourseToCourseDTO implements Converter<Course, CourseEntityDTO> {
    @Override
    public CourseEntityDTO convert(Course course) {

        CourseEntityDTO courseDTO = new CourseEntityDTO();
        courseDTO.setName(course.getName());
        courseDTO.setId(course.getId());
        courseDTO.setCategory(course.getCategory().toDTO());
        courseDTO.setCertificate(course.isCertificate());
        courseDTO.setCertificateName(course.getCertificateName());
        courseDTO.setCompany(course.getCompany().toDTO());
        return courseDTO;
    }
}
