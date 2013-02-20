package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.CourseOption;
import nl.codebasesoftware.produx.domain.OptionCategory;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-2-13
 * Time: 21:44
 */
public interface CourseOptionDao extends GenericDao<CourseOption> {

    List<OptionCategory> getCategoriesWithOptions();
}
