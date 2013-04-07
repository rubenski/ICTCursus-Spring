package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableMyUserProfile;
import nl.codebasesoftware.produx.formdata.OtherUserProfileFormData;

import java.util.List;

/**
 * User: rvanloen
 * Date: 16-7-12
 * Time: 23:00
 */
public interface UserProfileService {
    UserProfile findByEmail(String email);
    void update(BindableMyUserProfile profile);
    List<UserProfile> findOthersInCompany(long companyId, UserProfile userProfile);
    UserProfile findById(long id);
    void update(UserProfile profile);
    void update(OtherUserProfileFormData profile);
}
