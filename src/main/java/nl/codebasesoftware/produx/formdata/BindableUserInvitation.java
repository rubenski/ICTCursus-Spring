package nl.codebasesoftware.produx.formdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 14:13
 */
public class BindableUserInvitation implements Serializable {

    private Long companyId;
    private String firstName;
    private String preposition;
    private String lastName;
    private String email;
    private String securityCode;
    private List<Long> roles = new ArrayList<Long>();
    private boolean activated;

    private boolean confirmationScreen;


    public BindableUserInvitation() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isConfirmationScreen() {
        return confirmationScreen;
    }

    public void setConfirmationScreen(boolean confirmationScreen) {
        this.confirmationScreen = confirmationScreen;
    }

}
