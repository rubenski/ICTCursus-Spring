package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.service.SecurityService;
import nl.codebasesoftware.produx.properties.Properties;
import nl.codebasesoftware.produx.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 15:11
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private Properties properties;

    @Autowired
    public SecurityServiceImpl(Properties properties) {
        this.properties = properties;
    }


    @Override
    public String getRandomPassword() {
        String passwordLengthString = properties.getProperty("password.autogenerated.length");
        int actualLength = Integer.parseInt(passwordLengthString);
        return SecurityUtil.randomAlphaNumericString(actualLength);
    }

    @Override
    public String getHash(String text) {
        return SecurityUtil.createShaHash(text);
    }
}
