package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.Click;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 13:32
 */
public interface ClickDao extends GenericDao<Click> {
    List<Click> findForCompanyAndMonth(long companyId, Calendar fromDay, Calendar toDay);
    List<Click> findClicksDuringLastEightHours(long courseId, String ua, String ip);
}
