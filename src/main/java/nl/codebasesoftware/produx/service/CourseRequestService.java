package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 13:25
 */
public interface CourseRequestService {

    List<CourseRequest> findForCompany(long companyId);

    CourseRequestEntityDTO findFull(long id);

    boolean belongsTo(CompanyEntityDTO company, CourseRequestEntityDTO courseRequest);

    List<CourseRequest> findAllDateSortedDesc();

    void setInvalid(Long id, boolean invalid);

    List<CourseRequestEntityDTO> findForMonth(long companyId, MonthAndYear monthAndYear);
}
