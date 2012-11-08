package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * User: rvanloen
 * Date: 8-11-12
 * Time: 0:47
 */
public class TextProperties {

    public String getTextProperty(String key, String language) {
        Properties properties = new Properties();

        try {
            String fileName = String.format("messages/messages_%s.properties", language);
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            properties.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return properties.getProperty(key);
    }
}
