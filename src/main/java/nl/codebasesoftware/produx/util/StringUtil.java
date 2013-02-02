package nl.codebasesoftware.produx.util;

/**
 * User: rvanloen
 * Date: 1-2-13
 * Time: 22:16
 */
public class StringUtil {

    public static boolean isNullOrEmpty(String string){
        if(string == null || string.length() == 0){
            return true;
        }

        return false;
    }
}
