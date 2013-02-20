package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.SystemPropertyDao;
import nl.codebasesoftware.produx.domain.SystemProperty;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:45
 */
@Repository
public class SystemPropertyDaoJpa extends GenericDaoJpa<SystemProperty> implements SystemPropertyDao {

    public SystemPropertyDaoJpa() {
        super(SystemProperty.class);
    }

    @Override
    public SystemProperty findByKey(String key) {
        String hql = "from SystemProperty where key = :key";
        Query query = entityManager.createQuery(hql);
        query.setParameter("key", key);
        return getSingleResult(query);
    }
}
