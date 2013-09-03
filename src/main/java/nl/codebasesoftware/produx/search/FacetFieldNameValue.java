package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-9-13
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class FacetFieldNameValue {


    private long value;
    private String fieldName;
    private String valueName;


    public FacetFieldNameValue(String fieldName, String valueName, long value) {
        this.fieldName = fieldName;
        this.valueName = valueName;
        this.value = value;
    }


    public long getValue() {
        return value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getValueName() {
        return valueName;
    }


    public String getNameKey(){
        return String.format("%s.%s.%s", "rangefacet", fieldName, valueName);
    }
}
