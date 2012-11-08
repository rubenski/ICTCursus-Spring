package nl.codebasesoftware.produx.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * User: rvanloen
 * Date: 19-9-12
 * Time: 10:34
 */
@StaticMetamodel(Company.class)
public class Company_ {
    public static volatile SingularAttribute<Category,  Long> id;
    public static volatile SingularAttribute<Category,  String> email;
    public static volatile SingularAttribute<Category,  String> password;
    public static volatile SingularAttribute<Category,  String> phone;
    public static volatile SingularAttribute<Category,  String> description;
    public static volatile SingularAttribute<Category,  String> name;
    public static volatile SingularAttribute<Category,  String> logo;
    public static volatile SetAttribute<Category, Course> courses;
}
