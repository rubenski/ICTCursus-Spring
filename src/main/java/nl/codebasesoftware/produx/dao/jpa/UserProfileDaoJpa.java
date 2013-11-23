package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;


@Repository
@SuppressWarnings("unchecked")
public class UserProfileDaoJpa extends GenericDaoJpa<UserProfile> implements UserProfileDao {

    Logger log = Logger.getLogger(UserProfileDaoJpa.class);

    public UserProfileDaoJpa() {
        super(UserProfile.class);
    }

    public UserProfile findByEmail(String email) throws DataAccessException, EntityNotFoundException {
        List<UserProfile> results;
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
    public List<UserProfile> findOthersInCompany(long companyId, long excludedProfileId) {
        log.debug("query start");
        Query query = entityManager.createQuery("from UserProfile up where up.company.id = :companyId and up.id != :excludeProfileId");
        query.setParameter("companyId", companyId);
        query.setParameter("excludeProfileId", excludedProfileId);
        List resultList = query.getResultList();
        log.debug("query end");
        return resultList;
    }

    @Override
    public UserProfile findAuthorByArticle(long articleId) {
        return (UserProfile) entityManager.createQuery("from UserProfile u inner join fetch u.articles a where a.id = :id")
                .setParameter("id", articleId)
                .getSingleResult();
    }

    @Override
    public UserProfile findAuthorByPage(long pageId) {
        return (UserProfile) entityManager.createQuery("from UserProfile u inner join fetch u.articles a inner join fetch a.pages ap where ap.id = :id")
                .setParameter("id", pageId)
                .getSingleResult();
    }

    @Override
    public UserProfile findCourseLockingUser(long courseId) {
        return getSingleResult(entityManager.createQuery("from UserProfile up where up.id = :id").setParameter("id", courseId));
    }

    @Override
    public UserProfile findWithCompany(Long id) {
        return getSingleResult(entityManager.createQuery("from UserProfile up inner join fetch up.company c where up.id = :id").setParameter("id", id));
    }

    @Override
    public List<UserProfile> findByRole(RoleName role) {
        return entityManager.createQuery("from UserProfile up inner join fetch up.roles roles where roles.systemName = :systemName")
                .setParameter("systemName", role).getResultList();
    }
}