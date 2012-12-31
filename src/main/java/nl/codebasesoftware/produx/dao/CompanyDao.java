package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Logo;

/**
 * User: rvanloen
 * Date: 8-11-12
 * Time: 2:31
 */
public interface CompanyDao extends GenericDao<Company> {

    void setLogo(Logo logo,  Long companyId);

}
