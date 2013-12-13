package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.ClickDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Click;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;
import nl.codebasesoftware.produx.properties.Properties;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.LinkClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

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
    public void registerClick(HttpServletRequest request) {
        Click click = requestToClick(request);
        clickDao.persist(click);
    }

    private Click requestToClick(HttpServletRequest request){

        int costPerLinkClick = properties.getCostPerLinkClick();

        String courseIdString = request.getParameter("courseId");
        long courseId = Long.parseLong(courseIdString);
        Course course = courseDao.find(courseId);
        String externalUrl = request.getParameter("url");

        Click click = new Click();

        click.setCommission(costPerLinkClick);
        click.setUserAgent(request.getHeader("User-Agent"));
        click.setTime(Calendar.getInstance());
        click.setIp(request.getRemoteAddr());
        click.setCourse(course);
        click.setExternalUrl(externalUrl);
        return click;

    }
}
