package nl.codebasesoftware.produx.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * User: rvanloen
 * Date: 15-7-12
 * Time: 16:12
 */
public class SecurityUtil {

    public static String createPasswordHash(String password){
        return DigestUtils.shaHex(password);
    }
}
