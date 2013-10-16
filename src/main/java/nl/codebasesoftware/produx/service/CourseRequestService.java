package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 13:25
 */
public interface CourseRequestService {

    CourseRequestEntityDTO saveRequest(CourseRequestFormData courseRequestFormData);

    List<CourseRequest> findForCompany(long companyId);

    CourseRequestEntityDTO findFull(long id);

    boolean belongsTo(Company company, CourseRequestEntityDTO courseRequest);

    List<CourseRequest> findAllDateSortedDesc();

    void setInvalid(Long id, boolean invalid);
}
