package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 8:33
 */
public class BindableUsenamePassword {

    private String username;
    private String password;
    private Long invitationId;

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
