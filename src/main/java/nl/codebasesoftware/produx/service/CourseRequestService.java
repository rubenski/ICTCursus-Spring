package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 13:25
 */
public interface CourseRequestService {

    void saveRequest(CourseRequestFormData courseRequestFormData);

    List<CourseRequest> findForCompany(long companyId);

    CourseRequest findById(long id);

    boolean belongsToCompany(CourseRequest courseRequest, Company company);

    boolean belongsTo(Company company, CourseRequest courseRequest);
}
