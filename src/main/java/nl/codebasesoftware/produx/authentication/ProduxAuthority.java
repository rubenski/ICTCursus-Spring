package nl.codebasesoftware.produx.authentication;

import org.springframework.security.core.GrantedAuthority;

/**
 * User: rvanloen
 * Date: 3-11-12
 * Time: 16:41
 */
public class ProduxAuthority implements GrantedAuthority {

    private String authority;

    public ProduxAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public int hashCode() {
        return authority.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof ProduxAuthority)) return false;
        return ((ProduxAuthority) obj).getAuthority().equals(authority);
    }
}
