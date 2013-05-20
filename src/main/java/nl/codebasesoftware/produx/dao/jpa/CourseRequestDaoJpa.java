package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CourseRequestDao;
import nl.codebasesoftware.produx.domain.CourseRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-5-13
 * Time: 13:29
 */
@Repository
public class CourseRequestDaoJpa extends GenericDaoJpa<CourseRequest> implements CourseRequestDao {

    public CourseRequestDaoJpa() {
        super(CourseRequest.class);
    }

    @Override
    public List<CourseRequest> findForCompany(long companyId) {
        return entityManager.createQuery("from CourseRequest cr inner join fetch cr.course c where c.company.id = :companyId order by cr.created desc")
                .setParameter("companyId", companyId).getResultList();
    }
}
