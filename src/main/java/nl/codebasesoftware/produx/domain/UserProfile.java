package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.RoleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import nl.codebasesoftware.produx.spring.authentication.ProduxAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class UserProfile implements DomainEntity, UserDetails {

    private Long id;
    private String email;
    private String passwordHash;
    private String firstName;
    private String preposition;
    private String lastName;
    private String phone;
    private Company company;
    private Set<Role> roles;
    private Set<Article> articles;
    private boolean enabled;

    @Transient
    private final String PERMISSION_PREFIX = "ROLE_PERM_";


    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    @Transient
    public String getPassword() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Column(nullable = false)
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

    @Column(nullable = false)
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

    @ManyToOne
    @JoinColumn(nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // Note: CascadeType.ALL does not remove orphans from the database on a many-to-many relationship, unfortunately
    // http://stackoverflow.com/questions/3055407/how-do-i-delete-orphan-entities-using-hibernate-and-jpa-on-a-many-to-many-relati
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Methods from interface UserDetails
     */
    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<ProduxAuthority> authorities = new HashSet<ProduxAuthority>();
        for (Role role : roles) {
            for (Right right : role.getRights()) {
                ProduxAuthority produxAuthority = new ProduxAuthority(PERMISSION_PREFIX + right.getName());
                authorities.add(produxAuthority);
            }
        }
        return authorities;
    }

    @Override
    @Transient
    public String getUsername() {
        return email;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Transient
    public String getFullNameFormal(){
        return preposition != null ? String.format("%s, %s  %s", lastName, firstName, preposition) : String.format("%s, %s", lastName, firstName);
    }

    @Transient
    public String getFullNameInformal(){
        return preposition != null ? String.format("%s %s %s", firstName, preposition, lastName) : String.format("%s %s", firstName, lastName);
    }

    @Transient
    public List<Long> getRoleIds(){
        List<Long> ids = new ArrayList<Long>();
        for (Role role : roles) {
            ids.add(role.getId());
        }
        return ids;
    }

    @Transient
    public boolean hasRole(RoleName roleName){
        for (Role role : roles) {
            if(roleName.equals(role.getSystemName())){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    @Transient
    public UserProfileEntityDTO toDTO() {
        UserProfileEntityDTO dto = new UserProfileEntityDTO();
        dto.setId(id);
        dto.setEmail(email);
        dto.setCompany(company.toDTO());
        dto.setEnabled(enabled);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setPasswordHash(passwordHash);
        dto.setPhone(phone);
        dto.setPreposition(preposition);
        dto.setRoles(getRolesAsDTOs());
        return dto;
    }

    @Transient
    public List<RoleEntityDTO> getRolesAsDTOs() {
        List<RoleEntityDTO> roleDTOs = new ArrayList<>();
        for (Role role : roles) {
            roleDTOs.add(role.toDTO());
        }
        return roleDTOs;
    }


}
