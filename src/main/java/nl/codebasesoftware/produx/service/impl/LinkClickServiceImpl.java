package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.ClickDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Click;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;
import nl.codebasesoftware.produx.properties.Properties;
import nl.codebasesoftware.produx.service.LinkClickService;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import nl.codebasesoftware.produx.util.collection.EntityCollectionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 13:23
 */
@Service
public class LinkClickServiceImpl implements LinkClickService {

    private Properties properties;
    private CourseDao courseDao;
    private ClickDao clickDao;

    @Autowired
    public LinkClickServiceImpl(Properties properties, CourseDao courseDao, ClickDao clickDao) {
        this.properties = properties;
        this.courseDao = courseDao;
        this.clickDao = clickDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void registerClickOrIgnore(HttpServletRequest request) {
        Click click = requestToClick(request);

        if(alreadyClickedDuringLastEightHours(click)){
            return;
        }

        clickDao.persist(click);
    }

    private boolean alreadyClickedDuringLastEightHours(Click click) {
        List<Click> clicksDuringLastEightHours = clickDao.findClicksDuringLastEightHours(click.getCourse().getId(), click.getUserAgent(), click.getIp());
        return clicksDuringLastEightHours.size() > 0;
    }

    @Override
    public List<ClickEntityDTO> findForCompanyAndMonth(long companyId, MonthAndYear monthAndYear) {
        return new EntityCollectionConverter<Click, ClickEntityDTO>().convert(clickDao.findForCompanyAndMonth(companyId, monthAndYear.getFirstDayOfMonth(), monthAndYear.getLastDayOfMonth()));
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
