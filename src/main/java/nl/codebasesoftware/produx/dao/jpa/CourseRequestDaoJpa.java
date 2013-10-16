package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CourseRequestDao;
import nl.codebasesoftware.produx.domain.CourseRequest;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 13:29
 */
@Repository
@SuppressWarnings("unchecked")
public class CourseRequestDaoJpa extends GenericDaoJpa<CourseRequest> implements CourseRequestDao {

    public CourseRequestDaoJpa() {
        super(CourseRequest.class);
    }

    @Override
    public List<CourseRequest> findForCompany(long companyId) {
        return entityManager.createQuery("from CourseRequest cr inner join fetch cr.course c where c.company.id = :companyId order by cr.created desc")
                .setParameter("companyId", companyId).getResultList();
    }

    @Override
    public List<CourseRequest> findAllDateSortedDesc() {
        return entityManager.createQuery("from CourseRequest cr inner join fetch cr.course c inner join fetch c.company comp order by cr.created desc")
                .getResultList();
    }

    @Override
    public void setInvalid(Long id, boolean invalid) {
        entityManager.createQuery("update CourseRequest c set c.invalid = :invalid where c.id = :id")
                .setParameter("invalid", invalid)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public CourseRequest findFull(long id) {
        return getSingleResult(entityManager.createQuery("from CourseRequest cr " +
                "inner join fetch cr.course course " +
                "inner join fetch course.company " +
                "where cr.id = :id").setParameter("id", id));
    }
}
