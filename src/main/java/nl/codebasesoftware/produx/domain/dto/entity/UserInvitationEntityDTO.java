package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.UserProfile;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class UserInvitationEntityDTO extends DomainEntityDTO {

    private Long id;
    private String firstName;
    private String preposition;
    private String lastName;
    private String securityCode;
    private String email;
    private CompanyEntityDTO company;
    private UserProfileEntityDTO invitedBy;
    private List<RoleEntityDTO> roles;
    private Date creationDate;
    private boolean activated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CompanyEntityDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyEntityDTO company) {
        this.company = company;
    }

    public UserProfileEntityDTO getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(UserProfileEntityDTO invitedBy) {
        this.invitedBy = invitedBy;
    }

    public List<RoleEntityDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntityDTO> roles) {
        this.roles = roles;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
