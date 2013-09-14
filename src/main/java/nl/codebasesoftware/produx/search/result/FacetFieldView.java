package nl.codebasesoftware.produx.search.result;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 16-8-13
 * Time: 9:01
 */
public class FacetFieldView {

    private String fieldName;
    private List<FacetFilterLink> filterLinks = new ArrayList<>();
    private boolean provideClearLink;

    public FacetFieldView(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public List<FacetFilterLink> getFilterLinks() {
        return filterLinks;
    }

    public void addValue(FacetFilterLink nameValue) {
        filterLinks.add(nameValue);
    }

    public String getFieldHeaderKey() {
        String s = String.format("faceting.header.%s", fieldName);
        return s;
    }

    public boolean getProvideClearLink() {
        return provideClearLink;
    }

    public void setProvideClearLink(boolean provideClearLink) {
        this.provideClearLink = provideClearLink;
    }
}
