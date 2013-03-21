package nl.codebasesoftware.produx.formdata;

import java.io.Serializable;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 14:13
 */
public class BindableUserInvitation implements Serializable {

    private String firstName;
    private String lastName;
    private String email;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
