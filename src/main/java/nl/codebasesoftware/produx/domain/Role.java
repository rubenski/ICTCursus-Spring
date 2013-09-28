package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.RightEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.RoleEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 15-8-12
 * Time: 11:39
 */
@Entity
public class Role implements DomainEntity {

    private Long id;
    private Set<Right> rights;
    private RoleName systemName;
    private String displayName;
    private boolean companyAdminRole;
    private boolean userAssignable;
    private Integer listRank;
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

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
    @Enumerated(EnumType.STRING)
    public RoleName getSystemName() {
        return systemName;
    }

    public void setSystemName(RoleName systemName) {
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
    public Integer getListRank() {
        return listRank;
    }

    public void setListRank(Integer listRank) {
        this.listRank = listRank;
    }

    public boolean isUserAssignable() {
        return userAssignable;
    }

    public void setUserAssignable(boolean userAssignable) {
        this.userAssignable = userAssignable;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    @Transient
    public boolean hasRight(String rightName) {
        for (Right right : rights) {
            if (right.getAuthority().equals(rightName)) {
                return true;
            }
        }
        return false;
    }

    @Transient
    @Override
    public RoleEntityDTO toDTO() {
        RoleEntityDTO dto = new RoleEntityDTO();
        dto.setCompanyAdminRole(companyAdminRole);
        dto.setDisplayName(displayName);
        dto.setId(id);
        dto.setListRank(listRank);
        dto.setRights(getRightDTOs());
        return dto;
    }

    @Transient
    private List<RightEntityDTO> getRightDTOs() {
        List<RightEntityDTO> righDTOs = new ArrayList<>();
        for (Right right : rights) {
            righDTOs.add(right.toDTO());
        }
        return righDTOs;
    }
}
