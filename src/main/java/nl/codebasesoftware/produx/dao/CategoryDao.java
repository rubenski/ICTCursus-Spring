package nl.codebasesoftware.produx.dao;


import nl.codebasesoftware.produx.domain.Category;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:45
 */
public interface CategoryDao extends GenericDao<Category> {
    Category findByName(String name);
}
