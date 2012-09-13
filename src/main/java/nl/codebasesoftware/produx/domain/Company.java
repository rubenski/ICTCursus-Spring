package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 20:55
 */
@Entity
public class Company implements DomainObject {

    private Long id;
    private Integer version;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private byte[] logo;
    private Set<Course> courses = new HashSet<Course>();

    @Override
    @Id
    @GeneratedValue
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

    @Lob
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @OneToMany(mappedBy = "company")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
