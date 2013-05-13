package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.HighlightedCourseDao;
import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;
import org.springframework.stereotype.Repository;

/**
 * User: rvanloen
 * Date: 11-5-13
 * Time: 11:08
 */
@Repository
public class HighlightedCourseDaoJpa extends GenericDaoJpa<HighlightedCourseOnCategory> implements HighlightedCourseDao {


    public HighlightedCourseDaoJpa() {
        super(HighlightedCourseOnCategory.class);
    }
}
