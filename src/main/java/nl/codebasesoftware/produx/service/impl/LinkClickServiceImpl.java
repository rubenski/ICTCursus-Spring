package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.ClickDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Click;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;
import nl.codebasesoftware.produx.net.mail.BudgetWarningMailer;
import nl.codebasesoftware.produx.properties.Properties;
import nl.codebasesoftware.produx.service.BudgetWarningService;
import nl.codebasesoftware.produx.service.InvoiceService;
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


    private ClickDao clickDao;

    @Autowired
    public LinkClickServiceImpl(ClickDao clickDao) {
        this.clickDao = clickDao;
    }

    @Override
    public List<ClickEntityDTO> findForMonth(long companyId, MonthAndYear monthAndYear) {
        return new EntityCollectionConverter<Click, ClickEntityDTO>().convert(clickDao.findForCompanyAndMonth(companyId, monthAndYear.getFirstDayOfMonth(), monthAndYear.getLastDayOfMonth()));
    }
}
