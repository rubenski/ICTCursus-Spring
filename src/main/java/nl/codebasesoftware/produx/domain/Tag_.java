package nl.codebasesoftware.produx.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

/**
 * User: rvanloen
 * Date: 28-9-12
 * Time: 1:48
 */
public class Tag_ {
    public static volatile SingularAttribute<Tag, Long> id;
    public static volatile SingularAttribute<Tag, String> name;
    public static volatile SingularAttribute<Tag, String> description;
    public static volatile SetAttribute<Tag, Course> courses;
}
