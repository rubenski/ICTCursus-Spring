package nl.codebasesoftware.produx.service.support;

import nl.codebasesoftware.produx.domain.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        Object user = authentication.getPrincipal();

        if(user instanceof UserProfile){
            UserProfile userProfile = (UserProfile) authentication.getPrincipal();
            return userProfile;
        }

        return null;
    }
}
