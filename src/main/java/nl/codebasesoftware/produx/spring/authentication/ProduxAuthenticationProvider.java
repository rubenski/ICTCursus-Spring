package nl.codebasesoftware.produx.spring.authentication;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 2-11-12
 * Time: 18:12
 */
@Component(value = "authenticationProvider")
public class ProduxAuthenticationProvider implements AuthenticationProvider {

    private UserProfileService userProfileService;


    @Autowired
    public ProduxAuthenticationProvider(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserProfileEntityDTO profile = userProfileService.findByEmail(authentication.getPrincipal().toString());

        if (profile == null) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        String suppliedPasswordHash = SecurityUtil.createShaHash(authentication.getCredentials().toString());

        if (!profile.getPasswordHash().equals(suppliedPasswordHash)) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(profile, null, profile.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
