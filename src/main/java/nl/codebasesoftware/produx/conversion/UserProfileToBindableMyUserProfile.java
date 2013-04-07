package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableMyUserProfile;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 4-4-13
 * Time: 22:42
 */
public class UserProfileToBindableMyUserProfile implements Converter<UserProfile, BindableMyUserProfile> {

    @Override
    public BindableMyUserProfile convert(UserProfile userProfile) {
        BindableMyUserProfile myUserProfile = new BindableMyUserProfile();
        myUserProfile.setId(userProfile.getId());
        myUserProfile.setCompanyId(userProfile.getCompany().getId());
        myUserProfile.setEmail(userProfile.getEmail());
        myUserProfile.setFirstName(userProfile.getFirstName());
        myUserProfile.setLastName(userProfile.getFirstName());
        myUserProfile.setPhone(userProfile.getPhone());
        myUserProfile.setPreposition(userProfile.getPreposition());
        return myUserProfile;
    }
}
