package nl.codebasesoftware.produx.properties;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * User: rvanloen
 * Date: 8-11-12
 * Time: 0:26
 */


public class Properties {

    Map<String, String> propertyMap = new HashMap<>();

    public Properties() {
    }

    public void setPropertyMap(Map propertyMap) {
        this.propertyMap = propertyMap;
    }

    public String getProperty(String key) {
        String value = propertyMap.get(key);
        return value;
    }

    public int getSearchResultsPerPage() {
        return Integer.parseInt(getProperty("courses.per.page"));
    }
}

