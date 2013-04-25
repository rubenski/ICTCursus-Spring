package nl.codebasesoftware.produx.service.support;

import nl.codebasesoftware.produx.domain.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * User: rvanloen
 * Date: 17-3-13
 * Time: 19:16
 */
public class CurrentUser {

    public static UserProfile get(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = (UserProfile) authentication.getPrincipal();
        return userProfile;
    }
}