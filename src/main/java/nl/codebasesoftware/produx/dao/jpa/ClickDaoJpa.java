package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.ClickDao;
import nl.codebasesoftware.produx.domain.Click;
import org.springframework.stereotype.Repository;

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
    public List<Click> findForCompanyAndMonth(long companyId, int month, int year) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
