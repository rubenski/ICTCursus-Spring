package nl.codebasesoftware.produx.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 17:46
 */
public class ProduxValidator {

    public static boolean isValidEmail(String email){
        Pattern p = Pattern.compile("([a-zA-Z0-9!#$%&'*+-/=?^_`{}][a-zA-Z0-9!#$%&'*+-/=?^_`{}]{2,50}\\.?)+@([0-9a-zA-Z]+[-\\.]?)+\\.([a-zA-Z]{2,4}[\\.]?)+");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidFirstName(String firstName){
        return isValidName(firstName);
    }

    public static boolean isValidLastName(String lastName){
        return isValidName(lastName);
    }

    private static boolean isValidName(String name){
        Pattern p = Pattern.compile("[a-zA-Z ']{2,20}");
        Matcher m = p.matcher(name);
        return m.matches();
    }
}
