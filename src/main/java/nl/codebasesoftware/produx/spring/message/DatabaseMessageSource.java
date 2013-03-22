package nl.codebasesoftware.produx.spring.message;

import org.springframework.context.support.AbstractMessageSource;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 17:03
 */


// TODO: You wanted to implement u custom message source that retrieves its texts from the database in order to get
    // rid of the message files that are hard to maintain

public class DatabaseMessageSource extends AbstractMessageSource {


    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        MessageFormat messageFormat = new MessageFormat("testtext");
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
