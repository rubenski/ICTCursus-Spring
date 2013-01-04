package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Logo;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


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

    @Override
    public void setLogo(Logo logo, Long companyId) {
        String q = "update Company c SET c.logo.fileExtension = :fileExtension, c.logo.fileName = :fileName, c.logo.fileType = :fileType where c.id = :companyId";

        Query query = entityManager.createQuery(q);
        query.setParameter("companyId", companyId);
        query.setParameter("fileExtension", logo.getFileExtension());
        query.setParameter("fileName", logo.getFileName());
        query.setParameter("fileType", logo.getFileType());
        query.executeUpdate();
    }
}
