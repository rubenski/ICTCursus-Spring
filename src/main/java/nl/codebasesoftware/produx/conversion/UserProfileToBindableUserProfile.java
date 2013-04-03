package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableUserProfile;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 1-4-13
 * Time: 23:34
 */
public class UserProfileToBindableUserProfile implements Converter<UserProfile, BindableUserProfile> {

    @Override
    public BindableUserProfile convert(UserProfile userProfile) {
        BindableUserProfile profile = new BindableUserProfile();
        profile.setCompanyId(userProfile.getCompany().getId());
        profile.setEmail(userProfile.getEmail());
        profile.setEmailEditable(false);
        profile.setFirstName(userProfile.getFirstName());
        profile.setLastName(userProfile.getLastName());
        profile.setPhone(userProfile.getPhone());
        profile.setPreposition(userProfile.getPreposition());
        profile.setRoles(userProfile.getRoleIds());
        profile.setPreposition(userProfile.getPreposition());
        profile.setEnabled(userProfile.isEnabled());
        return profile;
    }
}
