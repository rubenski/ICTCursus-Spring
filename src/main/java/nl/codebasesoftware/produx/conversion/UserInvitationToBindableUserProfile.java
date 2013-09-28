package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.formdata.OtherUserProfileFormData;
import org.springframework.core.convert.converter.Converter;


/**
 * User: rvanloen
 * Date: 30-3-13
 * Time: 19:27
 */
public class UserInvitationToBindableUserProfile implements Converter<UserInvitation, OtherUserProfileFormData> {


    @Override
    public OtherUserProfileFormData convert(UserInvitation invitation) {
        OtherUserProfileFormData userProfile = new OtherUserProfileFormData();
        // TODO: fix this
        return userProfile;
    }
}
