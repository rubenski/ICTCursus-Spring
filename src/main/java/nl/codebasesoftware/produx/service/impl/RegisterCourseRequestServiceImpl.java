package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.dao.CourseRequestDao;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import nl.codebasesoftware.produx.service.BudgetWarningService;
import nl.codebasesoftware.produx.service.RegisterCourseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: rvanloen
 * Date: 24-12-13
 * Time: 16:57
 */
@Service
public class RegisterCourseRequestServiceImpl implements RegisterCourseRequestService {


    private final ConversionService conversionService;
    private final CourseDao courseDao;
    private final CourseRequestDao courseRequestDao;
    private final BudgetWarningService budgetWarningService;

    @Autowired
    public RegisterCourseRequestServiceImpl(ConversionService conversionService, CourseDao courseDao,
                                            CourseRequestDao courseRequestDao, BudgetWarningService budgetWarningService) {
        this.conversionService = conversionService;
        this.courseDao = courseDao;
        this.courseRequestDao = courseRequestDao;
        this.budgetWarningService = budgetWarningService;
    }

    @Override
    @Transactional(readOnly = false)
    public CourseRequestEntityDTO saveRequest(CourseRequestFormData courseRequestFormData, CompanyEntityDTO company) {
        CourseRequest request = conversionService.convert(courseRequestFormData, CourseRequest.class);

        Course course = courseDao.find(courseRequestFormData.getId());

        request.setCurrentCoursePriceInCents(course.getPrice());
        request.setCurrentCommissionPercentage(company.getProductSettings().getPercentagePerRequest());
        request.setInvoicePriceInCents((int) Math.round(course.getPrice() * company.getProductSettings().getPercentagePerRequest() / 100));

        courseRequestDao.persist(request);

        budgetWarningService.checkBudgetAndSendWarningOrIgnore(company);

        CourseRequest full = courseRequestDao.findFull(request.getId());
        return full.toDTO();
    }
}
