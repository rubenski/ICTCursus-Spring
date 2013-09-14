package nl.codebasesoftware.produx.search.result;


import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;

/**
 * User: rvanloen
 * Date: 4-9-13
 * Time: 11:39
 */
public class RangeFacetFilterLink extends FacetFilterLink {


    private final int gap;
    private final int value;


    public RangeFacetFilterLink(String fieldName, int value, int count, Integer gap, CategoryEntityDTO categoryEntityDTO, SearchCriteria criteria) {
        super(fieldName, value, count, categoryEntityDTO, criteria);
        this.value = value;
        this.gap = gap;
    }


    @Override
    public String getNameKey() {
        return String.format("rangefacet.%s.%d", fieldName, value);
    }

    @Override
    public String getCompleteUrl() {
        return criteria.getFacetingUrlParameters(this);
    }

    @Override
    public String getUrlToken() {
        return String.format("%s:%s-%s", fieldName, value, value + gap);
    }
}
