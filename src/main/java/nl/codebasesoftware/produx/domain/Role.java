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
    private Set<Right> rights;
    private String systemName;
    private String displayName;
    private boolean companyAdminRole;
    private int listRank;



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
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(nullable = false)
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Right> getRights() {
        return rights;
    }

    public void setRights(Set<Right> rights) {
        this.rights = rights;
    }

    public boolean isCompanyAdminRole() {
        return companyAdminRole;
    }

    public void setCompanyAdminRole(boolean companyAdminRole) {
        this.companyAdminRole = companyAdminRole;
    }

    @Column(nullable = true)
    public int getListRank() {
        return listRank;
    }

    public void setListRank(int listRank) {
        this.listRank = listRank;
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
