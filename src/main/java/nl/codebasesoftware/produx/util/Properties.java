package nl.codebasesoftware.produx.util;

import java.util.Map;

/**
 * User: rvanloen
 * Date: 8-11-12
 * Time: 0:26
 */
public class Properties {

    Map<String, String> properties;

    public void setProperties(Map properties) {
        this.properties = properties;
    }

    public String getProperty(String key) {
        String value =  properties.get(key);
        return value;
    }
}

