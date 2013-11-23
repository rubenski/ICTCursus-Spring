package nl.codebasesoftware.produx.service.support;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * User: rvanloen
 * Date: 17-3-13
 * Time: 19:16
 */
public class CurrentUser {

    public static UserProfileEntityDTO get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object user = authentication.getPrincipal();

        if (user instanceof UserProfileEntityDTO) {
            UserProfileEntityDTO userProfile = (UserProfileEntityDTO) authentication.getPrincipal();
            return userProfile;
        }

        return null;
    }
}
