package nl.codebasesoftware.produx.util;

import java.util.Random;

/**
 * User: rvanloen
 * Date: 7-11-12
 * Time: 23:49
 */
public class NumberUtil {

    public static int randomInt(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(max - min + 1) + min;
        return randomNumber;
    }


}
