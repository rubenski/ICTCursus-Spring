package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.OtherUserProfileFormData;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 1-4-13
 * Time: 23:34
 */
public class UserProfileToBindableUserProfile implements Converter<UserProfile, OtherUserProfileFormData> {

    @Override
    public OtherUserProfileFormData convert(UserProfile userProfile) {
        OtherUserProfileFormData profile = new OtherUserProfileFormData();
        profile.setEmail(userProfile.getEmail());
        profile.setId(userProfile.getId());
        profile.setCompanyId(userProfile.getCompany().getId());
        profile.setRoles(userProfile.getRoleIds());
        return profile;
    }
}
