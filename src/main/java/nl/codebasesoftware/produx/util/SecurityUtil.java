package nl.codebasesoftware.produx.util;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

/**
 * User: rvanloen
 * Date: 15-7-12
 * Time: 16:12
 */
public class SecurityUtil {

    public static String createShaHash(String password) {
        return DigestUtils.shaHex(password);
    }

    public static String randomAlphaNumericString(int length) {
        String randomString = "";
        for (int i = 0; i < length; i++) {
            int randomInt = 0;
            if (randomInt(0, 10) % 2 == 0) {
                randomInt = randomInt(97, 122);
            } else {
                randomInt = randomInt(48, 57);
            }
            randomString += (char) randomInt;
        }

        return randomString;
    }

    public static int randomInt(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(max - min + 1) + min;
        return randomNumber;
    }
}
