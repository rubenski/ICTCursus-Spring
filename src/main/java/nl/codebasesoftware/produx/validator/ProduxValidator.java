package nl.codebasesoftware.produx.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 17:46
 */
public class ProduxValidator {

    private static final String NORMAL_TEXT_PATTERN = "[A-Za-z0-9'!@#*;{}()\\[\\]&\"'?,\\.></ -]";

    public static boolean isValidEmail(String email) {
        Pattern p = Pattern.compile("([a-zA-Z0-9!#$%&'*+-/=?^_`{}][a-zA-Z0-9!#$%&'*+-/=?^_`{}]{2,50}\\.?)+@([0-9a-zA-Z]+[-\\.]?)+\\.([a-zA-Z]{2,4}[\\.]?)+");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidFirstName(String firstName) {
        return isValidName(firstName);
    }

    public static boolean isValidLastName(String lastName) {
        return isValidName(lastName);
    }

    private static boolean isValidName(String name) {
        Pattern p = Pattern.compile("[a-zA-Z ']{2,20}");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean isValidPassword(String password) {
        Pattern p = Pattern.compile("^.*(?=.{8,15})(?=.*\\d)(?=.*[a-zA-Z]).*$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean isValidPhoneNumber(String phone) {
        Pattern p = Pattern.compile("[0-9 +-]{10,14}");
        Matcher m = p.matcher(phone);
        boolean matches = m.matches();
        return matches;
    }

    public static boolean isValidCompanyName(String companyName) {
        return isValidNormalText(companyName, 2, 50);
    }

    public static boolean isValidPreposition(String preposition) {

        if (preposition == null || preposition == "") {
            return true;
        }

        Pattern p = Pattern.compile("[A-Za-z' -]{1,10}");
        Matcher m = p.matcher(preposition);
        return m.matches();
    }

    public static boolean isValidArticleTitle(String articleTitle) {
        return isValidNormalText(articleTitle, 8, 50);
    }

    public static boolean isArticleTeaserLongEnough(String articleTeaser) {
        return articleTeaser == null ? false : articleTeaser.length() > 150;
    }

    public static boolean isArticleTeaserValid(String articleTeaser) {
        return isValidNormalText(articleTeaser, 150, 500);
    }

    public static boolean isValidMetaKeywords(String keywords) {
        if (keywords == null) {
            return false;
        }
        Pattern p = Pattern.compile("[A-Za-z0-9, -.']{10,60}");
        Matcher m = p.matcher(keywords);
        return m.matches();
    }

    public static boolean isValidMetaDescription(String description) {
        return isValidNormalText(description, 50, 200);
    }

    public static boolean isValidSuggestionText(String suggestionText) {
        return isValidNormalText(suggestionText, 30, 500);
    }

    public static boolean isValidTradeNumber(String tradeNumber){
        Pattern p = Pattern.compile("[0-9]{8}");
        Matcher m = p.matcher(tradeNumber);
        return m.matches();
    }

    private static boolean isValidNormalText(String text, int min, int max) {
        if (text == null) {
            return false;
        }
        Pattern p = Pattern.compile(String.format("%s{%d,%d}", NORMAL_TEXT_PATTERN, min, max));
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean isValidAddrress(String address) {
        if (address == null) {
            return false;
        }
        Pattern p = Pattern.compile("[A-Za-z0-9, -.']{3,50}");
        Matcher m = p.matcher(address);
        return m.matches();
    }

    public static boolean isValidCity(String city) {
        if (city == null) {
            return false;
        }
        Pattern p = Pattern.compile("[A-Za-z, -.']{3,50}");
        Matcher m = p.matcher(city);
        return m.matches();
    }

    public static boolean isValidVatNumber(String vatNumber) {
        if (vatNumber == null) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9A-Za-z\\. ]{10,20}");
        Matcher m = p.matcher(vatNumber);
        return m.matches();
    }

    public static boolean isValidZipCode(String zip) {
        String zipCodeRegex = "[0-9]{4}[A-Za-z]{0,2}";
        Pattern zipCodePattern = Pattern.compile(zipCodeRegex);
        Matcher matcher = zipCodePattern.matcher(zip);
        return  matcher.matches();
    }

    public static boolean isValidBudgetTriggerAmount(String budgetTriggerAmount) {
        Pattern p = Pattern.compile("[1-9]{1}[0-9]{1,3}");
        Matcher matcher = p.matcher(budgetTriggerAmount.toString());
        boolean match = matcher.matches();
        if(!match){
            return false;
        }

        return Integer.parseInt(budgetTriggerAmount) >= 10;
    }
}