package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.List;

/**
 * User: rvanloen
 * Date: 15-8-12
 * Time: 11:39
 */
@Entity
public class Role implements DomainObject {

    private Long id;
    private Integer version;
    private String name;
    private List<UserProfile> userProfiles;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setName(Roles role){
        this.name = role.toString();
    }

    @ManyToMany
    public List<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(List<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
}
