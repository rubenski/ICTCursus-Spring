package nl.codebasesoftware.produx.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 19:16
 */
@StaticMetamodel(Category.class)
public class Category_ {
    public static volatile SingularAttribute<Category, Long> id;
    public static volatile SingularAttribute<Category, Integer> version;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile SingularAttribute<Category, String> description;
    public static volatile SetAttribute<Category, Course> courses;
    public static volatile SingularAttribute<Category, Category> parent;
    public static volatile SingularAttribute<Category, Long> numberOfVisits;
    public static volatile SingularAttribute<Category, String> urlTitle;
    public static volatile SetAttribute<Category, Category> children;
}

