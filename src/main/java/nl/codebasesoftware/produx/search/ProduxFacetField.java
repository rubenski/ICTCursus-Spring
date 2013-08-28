package nl.codebasesoftware.produx.search;

import java.util.HashMap;
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
    Map<String, Long> values = new HashMap<>();

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Map<String, Long> getValues() {
        return values;
    }

    public void addValue(String name, long value) {
        values.put(name, value);
    }
}
