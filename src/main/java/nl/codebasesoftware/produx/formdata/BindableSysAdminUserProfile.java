package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 13-5-13
 * Time: 0:32
 */
public class BindableSysAdminUserProfile {

    private long id;
    private String email;
    private boolean enabled;
    private long companyId;
    private int remove;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }
}
