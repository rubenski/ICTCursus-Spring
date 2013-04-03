package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import nl.codebasesoftware.produx.formdata.BindableUserProfile;

import java.util.List;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time:2 21:45
 */

public interface UserInvitationService {
    void inviteUserForCurrentCompany(BindableUserInvitation invitation) throws ProduxServiceException;
    UserInvitation findBySecurityCode(String code);
    UserInvitation findByEmail(String email);
    UserProfile activateProfile(BindableUserProfile profile);
    List<UserInvitation> findByInviter(long inviterProfileId);

}
