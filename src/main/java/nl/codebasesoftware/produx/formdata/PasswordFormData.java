package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 21-11-13
 * Time: 0:16
 */
public class PasswordFormData {

    private String existingPassword;
    private String newPassword1;
    private String newPassword2;

    public String getExistingPassword() {
        return existingPassword;
    }

    public void setExistingPassword(String existingPassword) {
        this.existingPassword = existingPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}
