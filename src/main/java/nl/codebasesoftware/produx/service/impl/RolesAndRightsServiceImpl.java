package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.RolesAndRightsDao;
import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.service.RolesAndRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 18:55
 */
@Service
public class RolesAndRightsServiceImpl implements RolesAndRightService {

    private RolesAndRightsDao rolesAndRightsDao;

    @Autowired
    public RolesAndRightsServiceImpl(RolesAndRightsDao rolesAndRightsDao) {
        this.rolesAndRightsDao = rolesAndRightsDao;
    }

    @Override
    public List<Role> findCompanyRoles() {
        return rolesAndRightsDao.findCompanyRoles();
    }

    @Override
    public List<Role> findByIds(List<Long> roleIds) {
        return rolesAndRightsDao.findByIds(roleIds);
    }
}
