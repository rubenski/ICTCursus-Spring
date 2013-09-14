package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.RegionDao;
import nl.codebasesoftware.produx.domain.Region;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * User: rvanloen
 * Date: 11-11-12
 * Time: 14:12
 */
@Repository
@SuppressWarnings("unchecked")
public class RegionDaoJpa extends GenericDaoJpa<Region> implements RegionDao {

    public RegionDaoJpa() {
        super(Region.class);
    }

    @Override
    public List<Region> findRegionsBySubstring(String substring) {
        Query query = entityManager.createQuery("from Region r where r.name like :substring");
        query.setParameter("substring", "%" + substring + "%");
        return query.getResultList();
    }

    @Override
    public List<Region> findAll() {
        Query query = entityManager.createQuery("from Region r order by name");
        return query.getResultList();
    }
}
