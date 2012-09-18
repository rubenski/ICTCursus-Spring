package nl.codebasesoftware.produx.domain.metamodel;

import nl.codebasesoftware.produx.domain.Category;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 19:16
 */
@StaticMetamodel(Category.class)
public class Category_ {
    public static volatile SingularAttribute<Category, String> name;
}