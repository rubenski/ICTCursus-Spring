package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.Tag;

import java.util.Collection;
import java.util.List;

/**
 * User: rvanloen
 * Date: 28-9-12
 * Time: 1:42
 */
public interface TagDao extends GenericDao<Tag> {
    List<Tag> findTagsForCourses(Collection<Course> courses);
    List<Tag> findBySubString(String tagName);
    Tag findByName(String tagName);
}
