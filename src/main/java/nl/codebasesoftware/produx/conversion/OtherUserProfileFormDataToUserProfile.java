package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.RoleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.OtherUserProfileFormData;
import nl.codebasesoftware.produx.service.RolesAndRightService;
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

/**
 * User: rvanloen
 * Date: 31-3-13
 * Time: 3:27
 */
@Component
public class OtherUserProfileFormDataToUserProfile implements Converter<OtherUserProfileFormData, UserProfileEntityDTO> {


    private UserProfileService userProfileService;
    private RolesAndRightService rolesAndRightService;


    @Autowired
    public OtherUserProfileFormDataToUserProfile(UserProfileService userProfileService, RolesAndRightService rolesAndRightService) {
        this.rolesAndRightService = rolesAndRightService;
        this.userProfileService = userProfileService;
    }

    @Override
    public UserProfileEntityDTO convert(OtherUserProfileFormData otherUserProfileFormData) {

        List<RoleEntityDTO> roles = rolesAndRightService.findByIds(otherUserProfileFormData.getRoles());
        UserProfileEntityDTO userProfile = userProfileService.findById(otherUserProfileFormData.getId());

        userProfile.setEnabled(otherUserProfileFormData.isEnabled());
        userProfile.setRoles(roles);

        return userProfile;

    }

}
