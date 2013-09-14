package nl.codebasesoftware.produx.search.result;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;

/**
 * User: rvanloen
 * Date: 4-9-13
 * Time: 12:17
 */
public abstract class FacetFilterLink {

    protected final String fieldName;
    private Object value;
    protected final long count;
    protected final CategoryEntityDTO category;
    protected final SearchCriteria criteria;

    protected FacetFilterLink(String fieldName, Object value, long count, CategoryEntityDTO category, SearchCriteria criteria) {
        this.fieldName = fieldName;
        this.value = value;
        this.count = count;
        this.category = category;
        this.criteria = criteria;
    }

    public  Long getCount(){
        return count;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    public boolean hasDocuments(){
        return count > 0;
    }

    protected abstract String getNameKey();
    protected abstract String getCompleteUrl();
    public abstract String getUrlToken();

}
