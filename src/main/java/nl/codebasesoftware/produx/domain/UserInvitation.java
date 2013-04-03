package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 21:41
 */
@Entity
public class UserInvitation implements DomainObject {

    private Long id;
    private String firstName;
    private String preposition;
    private String lastName;
    private String securityCode;
    private String email;
    private Company company;
    private UserProfile invitedBy;
    private Set<Role> roles;
    private boolean activated;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Column(nullable = true)
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

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public UserProfile getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(UserProfile invitedBy) {
        this.invitedBy = invitedBy;
    }

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column(nullable = false)
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Transient
    public String getFullNameFormal(){
        return preposition != null ? String.format("%s, %s  %s", lastName, firstName, preposition) : String.format("%s, %s", lastName, firstName);
    }
}
