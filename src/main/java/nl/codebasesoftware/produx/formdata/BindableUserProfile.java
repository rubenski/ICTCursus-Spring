package nl.codebasesoftware.produx.formdata;

import com.sun.istack.internal.NotNull;
import nl.codebasesoftware.produx.domain.UserProfile;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * User: rvanloen
 * Date: 20-7-12
 * Time: 0:17
 */
public class BindableUserProfile {
    Long id;
    String firstName;
    String lastName;
    String email;
    String phone;
    String password;
    Long companyId;

    public BindableUserProfile() {
    }

    public BindableUserProfile(UserProfile userProfile) {
        this.firstName = userProfile.getFirstName();
        this.lastName = userProfile.getLastName();
        this.email = userProfile.getEmail();
        this.phone = userProfile.getPhone();
        this.password = userProfile.getPassword();
        // this.companyId = userProfile.getCompany().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 40, message="errors.requiredfield")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Size(min = 3, max = 40,  message="errors.requiredfield")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public static Collection<BindableUserProfile> bindableUserProfiles(Collection<UserProfile> userProfiles) {

        if(userProfiles == null){
            return Collections.emptyList();
        }

        Collection<BindableUserProfile> bindableUserProfiles = new ArrayList<BindableUserProfile>();
        for (UserProfile userProfile : userProfiles) {
            bindableUserProfiles.add(new BindableUserProfile(userProfile));
        }
        return bindableUserProfiles;
    }

    public UserProfile asUserProfile(){
        UserProfile profile = new UserProfile();
        profile.setPassword(password);
        profile.setEmail(email);
        profile.setFirstName(firstName);
        profile.setId(id);
        profile.setLastName(lastName);
        profile.setPhone(phone);
        return profile;
    }
}
