package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CourseRequestDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import nl.codebasesoftware.produx.service.CourseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 13:25
 */
@Service
public class CourseRequestServiceImpl implements CourseRequestService {


    private CourseRequestDao courseRequestDao;
    private ConversionService conversionService;

    @Autowired
    public CourseRequestServiceImpl(CourseRequestDao courseRequestDao, ConversionService conversionService) {
        this.courseRequestDao = courseRequestDao;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveRequest(CourseRequestFormData courseRequestFormData) {
        CourseRequest request = conversionService.convert(courseRequestFormData, CourseRequest.class);
        courseRequestDao.persist(request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRequest> findForCompany(long companyId) {
        return courseRequestDao.findForCompany(companyId);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseRequest findById(long id) {
        return courseRequestDao.find(id);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean belongsToCompany(CourseRequest courseRequest, Company company) {
        boolean equals = courseRequest.getCourse().getCompany().equals(company);
        return equals;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean belongsTo(Company company, CourseRequest courseRequest) {
        CourseRequest mergedRequest = courseRequestDao.merge(courseRequest);
        return mergedRequest.getCourse().getCompany().equals(company);
    }
}
