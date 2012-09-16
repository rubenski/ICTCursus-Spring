package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.service.helpers.CourseFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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
    public List<Category> findFirstLevelCategories() {
        Query query = entityManager.createQuery("select c from Category c left join fetch c.children where c.parent is null order by c.name");

        return query.getResultList();

    }

    @Override
    public List<Course> findCourses(CourseFilter filter) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        Expression<Category> param = builder.parameter(Category.class);


        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> from = query.from(Course.class);
        query.select(from);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (!filter.getCategories().isEmpty()) {
            Predicate categoriesPredicate = param.in(filter.getCategories());
            predicates.add(categoriesPredicate);
        }

        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);
        query.where(predicateArray);

        return entityManager.createQuery(query).getResultList();
    }

}
