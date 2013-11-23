package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.dto.entity.RoleEntityDTO;

import java.util.List;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 18:54
 */
public interface RolesAndRightService {

    List<RoleEntityDTO> findByIds(List<Long> roleIds);

    List<Role> findUserAssignableRoles();
}
