package nl.codebasesoftware.produx.util.pdf;

import nl.codebasesoftware.produx.util.Properties;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.FopFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
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

    public FopFactory getFactoryInstance(){
        return factory;
    }

    public FOUserAgent newUserAgent(){
        FOUserAgent foUserAgent = factory.newFOUserAgent();
        foUserAgent.setAuthor(properties.getProperty("site.name"));
        foUserAgent.setCreationDate(new Date());
        foUserAgent.setCreator(properties.getProperty("site.name"));
        return foUserAgent;
    }

    private void initFactory(){
        factory = FopFactory.newInstance();
        factory.setFontBaseURL(properties.getProperty("fop.font.basedir"));
    }
}
