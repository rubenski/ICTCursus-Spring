package nl.codebasesoftware.produx.domain.dto;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 28-8-13
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public class LogoUrl {

    public static String getSmallAbsUrl(long id) {
        return String.format("/logo/small/%d-cursus-logo.png", id);
    }

    public static String getNormalAbsUrl(long id) {
        return String.format("/logo/normal/%d-logo.png", id);
    }
}
