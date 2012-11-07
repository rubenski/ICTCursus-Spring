package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.util.SecurityUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserProfileDaoJpa extends GenericDaoJpa<UserProfile> implements UserProfileDao {

    public UserProfileDaoJpa() {
        super(UserProfile.class);
    }

    public UserProfile findByEmailAndPassword(String email, String password) throws DataAccessException, AuthenticationException {
        List<UserProfile> results = null;
        String hashedPassword = SecurityUtil.createPasswordHash(password);
        Query query = entityManager.createQuery("from UserProfile as p where p.email = :email and p.passwordHash = :password");
        query.setParameter("email", email);
        query.setParameter("password", hashedPassword);
        results = query.getResultList();
        if (results == null || results.size() <= 0) {
            throw new AuthenticationException("No users found");
        } else {
            return results.get(0);
        }
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