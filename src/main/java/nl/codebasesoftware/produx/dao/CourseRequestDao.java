package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 13:29
 */
public interface CourseRequestDao extends GenericDao<CourseRequest> {


    List<CourseRequest> findForCompany(long companyId);

    List<CourseRequest> findAllDateSortedDesc();

    void setInvalid(Long id, boolean invalid);

    CourseRequest findFull(long id);

    List<CourseRequest> findBetween(Calendar startDate, Calendar endDate);
}
