package nl.codebasesoftware.produx.util;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-8-13
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {

    public static boolean isNullOrEmpty(String string){
        if(string == null) return true;
        if(string.length() == 0) return true;
        return false;
    }
}
