package nl.codebasesoftware.produx.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * User: rvanloen
 * Date: 15-7-12
 * Time: 16:12
 */
public class SecurityUtil {

    public static String createShaHash(String password){
        return DigestUtils.shaHex(password);
    }

    public static String randomAlphaNumericString(int length) {
        String randomString = "";
        for(int i = 0; i < length; i++){
            int randomInt = 0;
            if(NumberUtil.randomInt(0, 10)%2 == 0){
                randomInt = NumberUtil.randomInt(97, 122);
            }else{
                randomInt = NumberUtil.randomInt(48, 57);
            }
            randomString += (char) randomInt;
        }

        return randomString;
    }
}
