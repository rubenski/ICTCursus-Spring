package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.ProfileStatus;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import nl.codebasesoftware.produx.formdata.BindableMyUserProfile;
import nl.codebasesoftware.produx.formdata.BindableSysAdminUserProfile;
import nl.codebasesoftware.produx.formdata.OtherUserProfileFormData;

import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 16-7-12
 * Time: 23:00
 */
public interface UserProfileService {

    UserProfileEntityDTO findByEmail(String email);

    void update(BindableMyUserProfile profile);

    void update(BindableSysAdminUserProfile bindableSysAdminUserProfile);

    List<UserProfile> findOthersInCompany(long companyId, long userId);

    UserProfileEntityDTO findById(long id);

    void update(UserProfileEntityDTO profile);

    void update(OtherUserProfileFormData profile);

    UserProfileEntityDTO findAuthorByArticle(long articleId);

    List<UserProfileEntityDTO> findByRole(RoleName role);

    UserProfileEntityDTO findAuthorByPage(long pageId);

    List<UserProfile> findAll();

    UserProfileEntityDTO findWithCompany(Long id);

    List<ProfileStatus> getProfileStatuses(Locale locale);

    void removeProfile(Long id);

    UserProfileEntityDTO getCurrentlyLoggedInUser();


}
