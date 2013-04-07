package nl.codebasesoftware.produx.formdata;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 8:33
 */
public class OtherUserProfileFormData {

    private Long id;
    private String email;
    private boolean emailEditable;
    private Long companyId;
    private List<Long> roles = new ArrayList<Long>();
    private boolean enabled;

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

    public boolean isEmailEditable() {
        return emailEditable;
    }

    public void setEmailEditable(boolean emailEditable) {
        this.emailEditable = emailEditable;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
}
