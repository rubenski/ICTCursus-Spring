package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Right;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
public class RoleEntityDTO extends DomainEntityDTO {

    private Long id;
    private List<RightEntityDTO> rights;
    private RoleName systemName;
    private String displayName;
    private boolean companyAdminRole;
    private boolean userAssignable;
    private Integer listRank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RightEntityDTO> getRights() {
        return rights;
    }

    public void setRights(List<RightEntityDTO> rights) {
        this.rights = rights;
    }

    public RoleName getSystemName() {
        return systemName;
    }

    public void setSystemName(RoleName systemName) {
        this.systemName = systemName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isCompanyAdminRole() {
        return companyAdminRole;
    }

    public void setCompanyAdminRole(boolean companyAdminRole) {
        this.companyAdminRole = companyAdminRole;
    }

    public boolean isUserAssignable() {
        return userAssignable;
    }

    public void setUserAssignable(boolean userAssignable) {
        this.userAssignable = userAssignable;
    }

    public Integer getListRank() {
        return listRank;
    }

    public void setListRank(Integer listRank) {
        this.listRank = listRank;
    }
}
