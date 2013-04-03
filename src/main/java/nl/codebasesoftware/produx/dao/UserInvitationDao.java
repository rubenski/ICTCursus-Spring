package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.UserInvitation;

import java.util.List;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 21:47
 */
public interface UserInvitationDao extends GenericDao<UserInvitation> {
    UserInvitation findByEmail(String email);

    UserInvitation findByCode(String code);

    List<UserInvitation> findByInviter(long inviterProfileId);
}
