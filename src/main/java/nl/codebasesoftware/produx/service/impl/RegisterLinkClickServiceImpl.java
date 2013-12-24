package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.ClickDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Click;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.formdata.CourseRequestFormData;
import nl.codebasesoftware.produx.properties.Properties;
import nl.codebasesoftware.produx.service.BudgetWarningService;
import nl.codebasesoftware.produx.service.RegisterLinkClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 24-12-13
 * Time: 16:46
 */
@Service
public class RegisterLinkClickServiceImpl implements RegisterLinkClickService {


    private BudgetWarningService budgetWarningService;
    private ClickDao clickDao;
    private CourseDao courseDao;
    private final Properties properties;

    @Autowired
    public RegisterLinkClickServiceImpl(BudgetWarningService budgetWarningService, ClickDao clickDao, Properties properties, CourseDao courseDao) {
        this.budgetWarningService = budgetWarningService;
        this.properties = properties;
        this.clickDao = clickDao;
        this.courseDao = courseDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void registerClickOrIgnore(HttpServletRequest request) {
        Click click = requestToClick(request);

        if(alreadyClickedDuringLastEightHours(click)){
            return;
        }

        clickDao.persist(click);

        budgetWarningService.checkBudgetAndSendWarningOrIgnore(click.getCourse().getCompany().toDTO());
    }

    private boolean alreadyClickedDuringLastEightHours(Click click) {
        List<Click> clicksDuringLastEightHours = clickDao.findClicksDuringLastEightHours(click.getCourse().getId(), click.getUserAgent(), click.getIp());
        return clicksDuringLastEightHours.size() > 0;
    }

    private Click requestToClick(HttpServletRequest request){

        int costPerLinkClick = properties.getCostPerLinkClick();

        String courseIdString = request.getParameter("courseId");
        long courseId = Long.parseLong(courseIdString);
        Course course = courseDao.find(courseId);
        String externalUrl = request.getParameter("url");

        Click click = new Click();

        click.setInvoicePriceInCents(costPerLinkClick);
        click.setUserAgent(request.getHeader("User-Agent"));
        click.setCreated(Calendar.getInstance());
        click.setIp(request.getRemoteAddr());
        click.setCourse(course);
        click.setExternalUrl(externalUrl);
        return click;

    }



}
