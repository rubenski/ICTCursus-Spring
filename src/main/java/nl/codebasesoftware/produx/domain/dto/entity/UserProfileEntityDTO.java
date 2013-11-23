package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Right;
import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import nl.codebasesoftware.produx.spring.authentication.ProduxAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Transient;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public class UserProfileEntityDTO extends DomainEntityDTO implements UserDetails {

    private Long id;
    private String email;
    private String passwordHash;
    private String firstName;
    private String preposition;
    private String lastName;
    private String phone;
    private CompanyEntityDTO company;
    private List<RoleEntityDTO> roles;
    private boolean enabled;


    private final String PERMISSION_PREFIX = "ROLE_PERM_";


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPreposition() {
        return preposition;
    }

    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CompanyEntityDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyEntityDTO company) {
        this.company = company;
    }

    public List<RoleEntityDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntityDTO> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<ProduxAuthority> authorities = new HashSet<ProduxAuthority>();
        for (RoleEntityDTO role : roles) {
            for (RightEntityDTO right : role.getRights()) {
                ProduxAuthority produxAuthority = new ProduxAuthority(PERMISSION_PREFIX + right.getName());
                authorities.add(produxAuthority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean hasRole(RoleName roleName) {
        for (RoleEntityDTO role : roles) {
            if (roleName.equals(role.getSystemName())) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public String getFullNameFormal() {
        return preposition != null ? String.format("%s, %s  %s", lastName, firstName, preposition) : String.format("%s, %s", lastName, firstName);
    }

    @Transient
    public String getFullNameInformal() {
        return preposition != null ? String.format("%s %s %s", firstName, preposition, lastName) : String.format("%s %s", firstName, lastName);
    }
}


