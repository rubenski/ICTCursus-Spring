package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.OptionCategory;

import java.util.List;

/**
 * User: rvanloen
 * Date: 18-2-13
 * Time: 21:42
 */
public interface OptionService {
    List<OptionCategory> findAllOptionCategories();
}
