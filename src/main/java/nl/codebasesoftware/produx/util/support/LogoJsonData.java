package nl.codebasesoftware.produx.util.support;


import java.io.Serializable;

/**
 * User: rvanloen
 * Date: 31-12-12
 * Time: 13:11
 */
public class LogoJsonData implements Serializable {

    private String base64EncodedData;
    private String dataTypeString;

    public LogoJsonData(String base64EncodedData, String dataTypeString) {
        this.base64EncodedData = base64EncodedData;
        this.dataTypeString = dataTypeString;
    }

    public String getBase64EncodedData() {
        return base64EncodedData;
    }

    public String getDataTypeString() {
        return dataTypeString;
    }
}
