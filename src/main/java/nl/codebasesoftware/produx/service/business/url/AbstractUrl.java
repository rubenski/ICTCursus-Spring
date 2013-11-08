package nl.codebasesoftware.produx.service.business.url;

import java.util.regex.Pattern;

/**
 * User: rvanloen
 * Date: 28-5-13
 * Time: 9:56
 */
public abstract class AbstractUrl {

    protected static String preprareStringForUrl(String title) {
        Pattern specialCharsPattern = Pattern.compile("([^a-zA-z0-9])");
        String s = specialCharsPattern.matcher(title).replaceAll("-");
        // Remove a potential trailing hyphen
        if (s.endsWith("-")) {
            s = s.substring(0, s.length() - 1);
        }

        String replacedRepeatingHyphens = s.replaceAll("[-]+", "-");
        String toLower = replacedRepeatingHyphens.toLowerCase();
        return toLower;
    }
}
