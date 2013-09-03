package nl.codebasesoftware.produx.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 16-8-13
 * Time: 9:01
 * To change this template use File | Settings | File Templates.
 */
public class ProduxFacetField {

    String fieldName;
    List<FacetFieldNameValue> values = new ArrayList<>();

    public ProduxFacetField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public List<FacetFieldNameValue> getValues() {
        return values;
    }

    public void addValue(FacetFieldNameValue nameValue) {
        values.add(nameValue);
    }

    public String getFieldHeaderKey() {
        String s = String.format("faceting.header.%s", fieldName);
        return s;
    }
}
