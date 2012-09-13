package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserProfile implements DomainObject {

    private Long id;
    private Integer version;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Company company;
    private Set<Role> roles;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public final Long getId() {
        return id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @ManyToOne
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToMany
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean hasRole(Roles role){
        for (Role assignedRole : roles) {
            if(assignedRole.getName().equals(role)){
                return true;
            }
        }
        return false;
    }
}
