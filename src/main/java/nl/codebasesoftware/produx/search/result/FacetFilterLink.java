package nl.codebasesoftware.produx.search.result;

import nl.codebasesoftware.produx.search.criteria.SearchCriteria;

/**
 * User: rvanloen
 * Date: 4-9-13
 * Time: 12:17
 */
public abstract class FacetFilterLink {

    protected String value;
    public String field;
    protected long count;
    protected SearchCriteria criteria;

    public Long getCount() {
        return count;
    }

    public String getField() {
        return field;
    }

    public boolean hasDocuments() {
        return count > 0;
    }

    public String getHeaderNameKey() {
        return String.format("facet.field.header.%s", field.toLowerCase());
    }

    public abstract String getCompleteUrl();

    public abstract String getUrlToken();

    public abstract String getLabel();

    public abstract String getHrefValue();


}
