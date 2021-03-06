package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.dao.CourseRequestDao;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import nl.codebasesoftware.produx.service.BudgetWarningService;
import nl.codebasesoftware.produx.service.CourseRequestService;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import nl.codebasesoftware.produx.util.collection.EntityCollectionConverter;
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

    @Autowired
    public CourseRequestServiceImpl(CourseRequestDao courseRequestDao) {
        this.courseRequestDao = courseRequestDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRequest> findForCompany(long companyId) {
        return courseRequestDao.findForCompany(companyId);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseRequestEntityDTO findFull(long id) {
        return courseRequestDao.findFull(id).toDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean belongsTo(CompanyEntityDTO company, CourseRequestEntityDTO courseRequest) {
        CourseRequest request = courseRequestDao.findFull(courseRequest.getId());
        return request.getCourse().getCompany().getId().equals(company.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRequest> findAllDateSortedDesc() {
        return courseRequestDao.findAllDateSortedDesc();
    }

    @Override
    @Transactional(readOnly = false)
    public void setInvalid(Long id, boolean invalid) {
        courseRequestDao.setInvalid(id, invalid);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<CourseRequestEntityDTO> findForMonth(long companyId, MonthAndYear monthAndYear) {
        List<CourseRequest> requests = courseRequestDao.findBetween(companyId, monthAndYear.getFirstDayOfMonth(), monthAndYear.getLastDayOfMonth());
        return new EntityCollectionConverter<CourseRequest, CourseRequestEntityDTO>().convert(requests);
    }

}
