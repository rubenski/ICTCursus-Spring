package nl.codebasesoftware.produx.search.result;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;

/**
 * User: rvanloen
 * Date: 3-9-13
 * Time: 17:05
 */
public class NormalFacetFilterLink extends FacetFilterLink {

    private final String fieldName;
    private final String value;


    public NormalFacetFilterLink(String fieldName, String value, long count, CategoryEntityDTO category, SearchCriteria searchCriteria) {
        super(fieldName, value, count, category, searchCriteria);
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public String getNameKey() {
        return String.format("normalfacet.%s.%s", fieldName, value);
    }

    @Override
    public String getCompleteUrl() {
        return criteria.getFacetingUrlParameters(this);
    }

    @Override
    public String getUrlToken() {
        return String.format("%s:%s", fieldName, value);
    }
}
