package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.support.FacetContent;

/**
 * User: rvanloen
 * Date: 26-9-12
 * Time: 23:47
 */
public interface FacetService {
    FacetContent findFacetContent(Category category);
}
