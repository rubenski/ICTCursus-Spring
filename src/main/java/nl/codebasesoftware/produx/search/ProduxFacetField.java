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
public class ProduxFacetField {

    String fieldName;
    List<FacetFieldView> values = new ArrayList<>();

    public ProduxFacetField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public List<FacetFieldView> getValues() {
        return values;
    }

    public void addValue(FacetFieldView nameValue) {
        values.add(nameValue);
    }

    public String getFieldHeaderKey() {
        String s = String.format("faceting.header.%s", fieldName);
        return s;
    }
}
