package nl.codebasesoftware.produx.search;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 11:57
 * To change this template use File | Settings | File Templates.
 */
public class SolrCategoryName {

    private static final String CATEGORY_DELIMITER = "---";

    public static String create(CategoryEntityDTO categoryDTO){
        return String.format("%s%s%s", categoryDTO.getUrlTitle(), CATEGORY_DELIMITER, categoryDTO.getName());
    }
}
