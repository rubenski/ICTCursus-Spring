package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.formdata.BindableUserProfile;
import org.springframework.core.convert.converter.Converter;



/**
 * User: rvanloen
 * Date: 30-3-13
 * Time: 19:27
 */
public class UserInvitationToBindableUserProfile implements Converter<UserInvitation, BindableUserProfile> {


    @Override
    public BindableUserProfile convert(UserInvitation invitation) {
        BindableUserProfile userProfile = new BindableUserProfile();
        userProfile.setEmail(invitation.getEmail());
        userProfile.setInvitationId(invitation.getId());
        userProfile.setFirstName(invitation.getFirstName());
        userProfile.setLastName(invitation.getLastName());
        userProfile.setEmailEditable(false);
        return userProfile;
    }
}
