package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.UserProfile;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserProfileDaoJpa extends GenericDaoJpa<UserProfile> implements UserProfileDao {

    Logger log = Logger.getLogger(UserProfileDaoJpa.class);

    public UserProfileDaoJpa() {
        super(UserProfile.class);
    }

    public UserProfile findByEmail(String email) throws DataAccessException, EntityNotFoundException {
        List<UserProfile> results = null;
        Query query = entityManager.createQuery("from UserProfile as up where up.email = :email");
        query.setParameter("email", email);
        results = query.getResultList();
        if (results == null || results.size() <= 0) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    public List<UserProfile> findByCompany(long companyId) {
        log.debug("query start");
        Query query = entityManager.createQuery("from UserProfile up where up.company.id = :companyId");
        query.setParameter("companyId", companyId);
        List resultList = query.getResultList();
        log.debug("query end");
        return resultList;
    }
}