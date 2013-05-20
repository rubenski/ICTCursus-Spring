package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 13:41
 */
public class CourseRequestFormDataToNewCourseRequest implements Converter<CourseRequestFormData, CourseRequest> {

    private CourseDao courseDao;

    @Autowired
    public CourseRequestFormDataToNewCourseRequest(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public CourseRequest convert(CourseRequestFormData courseRequestFormData) {
        Course course = courseDao.find(courseRequestFormData.getCourseId());

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourse(course);
        courseRequest.setEmail(courseRequestFormData.getEmail());
        courseRequest.setMessage(courseRequestFormData.getMessage());
        courseRequest.setRequesterName(courseRequestFormData.getRequesterName());
        courseRequest.setNumberOfParticipants(courseRequestFormData.getNumberOfParticipants());
        courseRequest.setCourseName(courseRequestFormData.getCourseName());
        courseRequest.setPrefix(courseRequestFormData.getPrefix());
        courseRequest.setCreated(Calendar.getInstance());

        return courseRequest;
    }
}
