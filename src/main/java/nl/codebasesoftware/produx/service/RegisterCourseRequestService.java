package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;

/**
 * User: rvanloen
 * Date: 24-12-13
 * Time: 16:56
 */
public interface RegisterCourseRequestService {
    CourseRequestEntityDTO saveRequest(CourseRequestFormData courseRequestFormData, CompanyEntityDTO company);
}
