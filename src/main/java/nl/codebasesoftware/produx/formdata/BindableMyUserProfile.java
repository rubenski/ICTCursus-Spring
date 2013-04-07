package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 8:33
 */
public class BindableMyUserProfile {

    private Long id;
    private String email;
    private String phone;
    private String firstName;
    private String preposition;
    private String lastName;
    private Long companyId;
    private boolean emailEditable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPreposition() {
        return preposition;
    }

    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }

    public boolean isEmailEditable() {
        return emailEditable;
    }

    public void setEmailEditable(boolean emailEditable) {
        this.emailEditable = emailEditable;
    }
}
