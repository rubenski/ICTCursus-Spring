package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.RolesAndRightsDao;
import nl.codebasesoftware.produx.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: rvanloen
 * Date: 28-3-13
 * Time: 18:57
 */
@Repository
public class RolesAndRightsDaoJpa extends GenericDaoJpa<Role> implements RolesAndRightsDao{

    public RolesAndRightsDaoJpa() {
        super(Role.class);
    }

    @Override
    public List<Role> findByIds(List<Long> roleIds) {
        return entityManager.createQuery("from Role r where r.id in (:ids)").setParameter("ids", roleIds).getResultList();
    }

    @Override
    public List<Role> findUserAssignableRoles() {
        return entityManager.createQuery("from Role r where r.userAssignable = true").getResultList();
    }
}
