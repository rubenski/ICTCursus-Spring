package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.UserInvitationDao;
import nl.codebasesoftware.produx.domain.UserInvitation;
import org.springframework.stereotype.Repository;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 21:49
 */
@Repository
public class UserInvitationDaoJpa extends GenericDaoJpa<UserInvitation> implements UserInvitationDao {

    public UserInvitationDaoJpa() {
        super(UserInvitation.class);
    }

    @Override
    public UserInvitation findByEmail(String email) {
        return getSingleResult(entityManager.createQuery("from UserInvitation ui where ui.email = :email").setParameter("email", email));
    }

    @Override
    public UserInvitation findByCode(String code) {
        return getSingleResult(entityManager.createQuery("from UserInvitation ui where ui.securityCode = :code").setParameter("code", code));
    }

}
