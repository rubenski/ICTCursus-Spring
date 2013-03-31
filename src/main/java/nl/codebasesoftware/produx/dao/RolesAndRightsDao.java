package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Role;

import java.util.List;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 18:56
 */
public interface RolesAndRightsDao extends GenericDao<Role> {
    List<Role> findCompanyRoles();

    List<Role> findByIds(List<Long> roleIds);
}
