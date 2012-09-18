package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Category;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 18:43
 */
public interface CategoryService {
    Category findByName(String name);
}
