package nl.codebasesoftware.produx.util;


import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 8-11-12
 * Time: 17:20
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData {

    private UserProfile userProfile;
    private Company company;

    @Scope(value = "session")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
