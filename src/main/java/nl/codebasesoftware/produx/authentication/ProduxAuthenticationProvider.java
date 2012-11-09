package nl.codebasesoftware.produx.authentication;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.util.SecurityUtil;
import nl.codebasesoftware.produx.util.SessionData;
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
    private SessionData sessionData;
    private CompanyService companyService;


    @Autowired
    public ProduxAuthenticationProvider(UserProfileService userProfileService, SessionData sessionData, CompanyService companyService) {
        this.userProfileService = userProfileService;
        this.sessionData = sessionData;
        this.companyService = companyService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserProfile profile = userProfileService.findByEmail(authentication.getPrincipal().toString());

        if(profile == null){
            throw new UsernameNotFoundException(String.format("Invalid credentials", authentication.getPrincipal()));
        }

        String suppliedPasswordHash = SecurityUtil.createPasswordHash(authentication.getCredentials().toString());

        if(!profile.getPasswordHash().equals(suppliedPasswordHash)){
            throw new BadCredentialsException("Invalid credentials");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(profile, null, profile.getAuthorities());

        Company company = companyService.findByUserProfile(profile);

        // Set session data
        sessionData.setUserProfile(profile);
        sessionData.setCompany(company);

        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
