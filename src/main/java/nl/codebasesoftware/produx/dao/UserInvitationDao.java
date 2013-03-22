package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.UserInvitation;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 21:47
 */
public interface UserInvitationDao extends GenericDao<UserInvitation> {
    UserInvitation findByEmail(String email);
}
