package nl.codebasesoftware.produx.search;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-9-13
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class NormalFacetFilterLink extends FacetFilterLink {

    private final String fieldName;
    private final String value;
    private final long count;



    public NormalFacetFilterLink(String fieldName, String value, long count, CategoryEntityDTO category, SearchCriteria searchCriteria) {
        super(fieldName, value, count, category, searchCriteria);
        this.fieldName = fieldName;
        this.value = value;
        this.count = count;
    }

    @Override
    public String getNameKey(){
        return String.format("normalfacet.%s.%d", fieldName, value);
    }

    @Override
    public String getUrl(){
        return criteria.getFacetingUrlParameters(this);
    }

    @Override
    public String asUrlToken() {
        return String.format("%s:%s", fieldName, value);
    }
}
