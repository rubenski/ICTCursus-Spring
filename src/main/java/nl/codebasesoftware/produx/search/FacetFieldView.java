package nl.codebasesoftware.produx.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 16-8-13
 * Time: 9:01
 * To change this template use File | Settings | File Templates.
 */
public class FacetFieldView {

    String fieldName;
    List<FacetFilterLink> values = new ArrayList<>();

    public FacetFieldView(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public List<FacetFilterLink> getValues() {
        return values;
    }

    public void addValue(FacetFilterLink nameValue) {
        values.add(nameValue);
    }

    public String getFieldHeaderKey() {
        String s = String.format("faceting.header.%s", fieldName);
        return s;
    }
}
