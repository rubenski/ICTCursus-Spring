package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CourseOptionDao;
import nl.codebasesoftware.produx.domain.CourseOption;
import nl.codebasesoftware.produx.domain.OptionCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-2-13
 * Time: 21:45
 */
@Repository
@SuppressWarnings("unchecked")
public class CourseOptionDaoJpa extends GenericDaoJpa<CourseOption> implements CourseOptionDao {


    public CourseOptionDaoJpa() {
        super(CourseOption.class);
    }

    @Override
    public List<OptionCategory> getCategoriesWithOptions() {
        return entityManager.createQuery("select distinct oc from OptionCategory oc join fetch oc.options oo order by oc.displayRank").getResultList();
    }
}
