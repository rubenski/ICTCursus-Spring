package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 15-8-12
 * Time: 11:39
 */
@Entity
public class Role implements DomainObject {

    private Long id;
    private String name;
    private Set<Right> rights;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setName(Roles role){
        this.name = role.toString();
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Right> getRights() {
        return rights;
    }

    public void setRights(Set<Right> rights) {
        this.rights = rights;
    }

    @Transient
    public boolean hasRight(String rightName){
        for (Right right : rights) {
            if(right.getAuthority().equals(rightName)){
                return true;
            }
        }
        return false;
    }
}
