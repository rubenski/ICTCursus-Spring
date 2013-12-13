package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.ClickDao;
import nl.codebasesoftware.produx.domain.Click;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 13:35
 */
@Repository
public class ClickDaoJpa extends GenericDaoJpa<Click> implements ClickDao {

    public ClickDaoJpa() {
        super(Click.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Click> findForCompanyAndMonth(long companyId, Calendar fromDay, Calendar toDay) {
        return entityManager.createQuery("from Click c inner join fetch c.course co " +
                "where co.company.id = :companyId " +
                "and c.created >= :fromDay " +
                "and c.created <= :toDay")
                .setParameter("companyId", companyId)
                .setParameter("fromDay", fromDay)
                .setParameter("toDay", toDay)
                .getResultList();
    }

    public List<Click> findClicksDuringLastEightHours(long courseId, String ua, String ip){

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -8);

        return entityManager.createQuery("from Click c where c.course.id = :courseId and c.userAgent = :ua and c.ip = :ip and c.created > :eightHoursAgo ")
                .setParameter("courseId", courseId)
                .setParameter("ua", ua)
                .setParameter("ip", ip)
                .setParameter("eightHoursAgo", cal).getResultList();
    }
}
