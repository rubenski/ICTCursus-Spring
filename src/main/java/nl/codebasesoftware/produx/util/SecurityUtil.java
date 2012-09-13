package nl.codebasesoftware.produx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: rvanloen
 * Date: 15-7-12
 * Time: 16:12
 */
public class SecurityUtil {
    public static String createPasswordHash(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = md.digest();
        String hashedPassword = hash.toString();
        return hashedPassword;
    }
}
