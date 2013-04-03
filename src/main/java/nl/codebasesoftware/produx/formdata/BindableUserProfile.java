package nl.codebasesoftware.produx.formdata;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 8:33
 */
public class BindableUserProfile {

    private String email;
    private Long invitationId;
    private String password1;
    private String password2;
    private String phone;
    private String firstName;
    private String preposition;
    private String lastName;
    private boolean emailEditable;
    private Long companyId;
    private List<Long> roles = new ArrayList<Long>();

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

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
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

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getEmailEditable() {
        return emailEditable;
    }

    public void setEmailEditable(boolean emailEditable) {
        this.emailEditable = emailEditable;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
}
