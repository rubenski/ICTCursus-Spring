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

    public static boolean isValidPassword(String password){
        Pattern p = Pattern.compile("^.*(?=.{8,15})(?=.*\\d)(?=.*[a-zA-Z]).*$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean isValidPhoneNumber(String phone){
        Pattern p = Pattern.compile("[0-9 +-]{10,14}");
        Matcher m = p.matcher(phone);
        boolean matches = m.matches();
        return matches;
    }

    public static boolean isValidPreposition(String preposition){

        if(preposition == null || preposition == ""){
            return true;
        }

        Pattern p = Pattern.compile("[A-Za-z' -]{1,10}");
        Matcher m = p.matcher(preposition);
        return m.matches();
    }

    public static boolean isValidArticleTitle(String articleTitle){
        if(articleTitle == null || articleTitle == ""){
            return false;
        }
        Pattern p = Pattern.compile("[A-Za-z0-9'!*{}\\[\\]&\"'?,\\.></ -]{8,50}");
        Matcher m = p.matcher(articleTitle);
        return  m.matches();
    }

    public static boolean isArticleTeaserLongEnough(String articleTeaser){
        return articleTeaser == null ? false : articleTeaser.length() > 150;
    }


    public static boolean isArticleTeaserValid(String articleTeaser){
        if(articleTeaser == null || articleTeaser == ""){
            throw new IllegalArgumentException("Please first test if the article teaser is long enough");
        }
        Pattern p = Pattern.compile("[A-Za-z0-9'!*{}\\[\\]&\"'?,\\.></ -]{150,500}");
        Matcher m = p.matcher(articleTeaser);
        return  m.matches();
    }
}
