package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:41
 */
@Repository
public class CourseDaoJpa extends GenericDaoJpa<Course> implements CourseDao {

    public CourseDaoJpa() {
        super(Course.class);
    }

    @Override
    public List<Category> findCategorizedCourses() {
        Query query = entityManager.createQuery("from Category as c join fetch c.courses order by c.name");
        return query.getResultList();
    }

}
