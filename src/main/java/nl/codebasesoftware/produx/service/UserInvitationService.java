package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.formdata.BindableUserInvitation;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time:2 21:45
 */

public interface UserInvitationService {
    void inviteUserForCurrentCompany(BindableUserInvitation invitation);
    UserInvitation findBySecurityCode(String code);
}
