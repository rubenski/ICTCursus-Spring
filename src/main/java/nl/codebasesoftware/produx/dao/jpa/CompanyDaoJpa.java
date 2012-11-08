package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.domain.Company;
import org.springframework.stereotype.Repository;

/**
 * User: rvanloen
 * Date: 8-11-12
 * Time: 2:31
 */
@Repository
public class CompanyDaoJpa extends GenericDaoJpa<Company> implements CompanyDao {

    public CompanyDaoJpa() {
        super(Company.class);
    }


}
