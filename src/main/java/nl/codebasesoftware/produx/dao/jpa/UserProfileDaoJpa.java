package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.UserProfile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserProfileDaoJpa extends GenericDaoJpa<UserProfile> implements UserProfileDao {

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

}