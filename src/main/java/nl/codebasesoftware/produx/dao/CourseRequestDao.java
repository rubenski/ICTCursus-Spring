package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.CourseRequest;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 13:29
 */
public interface CourseRequestDao extends GenericDao<CourseRequest> {


    List<CourseRequest> findForCompany(long companyId);
}
