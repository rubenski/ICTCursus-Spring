package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CourseRequestDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import nl.codebasesoftware.produx.service.CourseRequestService;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import nl.codebasesoftware.produx.util.collection.EntityCollectionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
    public CourseRequestEntityDTO saveRequest(CourseRequestFormData courseRequestFormData) {
        CourseRequest request = conversionService.convert(courseRequestFormData, CourseRequest.class);
        courseRequestDao.persist(request);
        CourseRequest full = courseRequestDao.findFull(request.getId());
        return full.toDTO();
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
    public boolean belongsTo(Company company, CourseRequestEntityDTO courseRequest) {
        CourseRequest request = courseRequestDao.findFull(courseRequest.getId());
        return request.getCourse().getCompany().equals(company);
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

        Locale locale = LocaleContextHolder.getLocale();
        Calendar firstDay = Calendar.getInstance(locale);
        firstDay.set(Calendar.YEAR, monthAndYear.getYear());
        firstDay.set(Calendar.MONTH, monthAndYear.getMonth() -1);
        firstDay.set(Calendar.DAY_OF_MONTH, firstDay.getActualMinimum(Calendar.DAY_OF_MONTH));

        Calendar lastDay = Calendar.getInstance(locale);
        lastDay.set(Calendar.YEAR, monthAndYear.getYear());
        lastDay.set(Calendar.MONTH, monthAndYear.getMonth() -1);
        lastDay.set(Calendar.DAY_OF_MONTH, lastDay.getActualMaximum(Calendar.DAY_OF_MONTH));

        List<CourseRequest> requests = courseRequestDao.findBetween(companyId, firstDay, lastDay);

        return new EntityCollectionConverter<CourseRequest, CourseRequestEntityDTO>().convert(requests);
    }

}
