package nl.codebasesoftware.produx.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Date;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 22:35
 */
public class Course_ {
    
    public static volatile SingularAttribute<Course, Long> id;
    public static volatile SingularAttribute<Course, String> name;
    public static volatile SingularAttribute<Course, String> shortDescription;
    public static volatile SingularAttribute<Course, String> longDescription;
    public static volatile SingularAttribute<Course, String> duration;
    public static volatile SetAttribute<Course, Region> regions;
    public static volatile SingularAttribute<Course, Double> price;
    public static volatile SingularAttribute<Course, Company> company;
    public static volatile SingularAttribute<Course, Date> lastUpdated;
    public static volatile SingularAttribute<Course, Category> category;
    public static volatile SetAttribute<Course, Tag> tags;
    public static volatile SetAttribute<Course, Experience> experiences;
}
