package nl.codebasesoftware.produx.search;


import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 4-9-13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
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
    public String getUrl() {
        return criteria.getFacetingUrlParameters(this);
    }

    @Override
    public String asUrlToken() {
        return String.format("%s:%s-%s", fieldName, value, value + gap);
    }
}
