package nl.codebasesoftware.produx.util.pdf;

import nl.codebasesoftware.produx.properties.Properties;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.FopFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * User: rvanloen
 * Date: 3-11-13
 * Time: 10:54
 */
@Component
public class FopConfigurator {

    private Properties properties;
    private FopFactory factory;

    @Autowired
    public FopConfigurator(Properties properties) {
        this.properties = properties;
        initFactory();
    }

    public FopFactory getFactoryInstance() {
        return factory;
    }

    public FOUserAgent newUserAgent() {
        FOUserAgent foUserAgent = factory.newFOUserAgent();
        foUserAgent.setAuthor(properties.getProperty("site.name"));
        foUserAgent.setCreator(properties.getProperty("site.name"));
        foUserAgent.setCreationDate(new Date());
        return foUserAgent;
    }

    private void initFactory() {
        DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
        InputStream in = getClass().getResourceAsStream("/config/fop-config.xml");
        Configuration config = null;

        try {
            config = builder.build(in);
        } catch (SAXException | IOException | ConfigurationException e) {
            e.printStackTrace();
        }

        factory = FopFactory.newInstance();
        factory.setUserConfig(config);
        factory.setFontBaseURL(properties.getProperty("fop.font.basedir"));

    }


}
