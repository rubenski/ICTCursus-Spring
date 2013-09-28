package nl.codebasesoftware.produx.service;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 15:10
 */
public interface SecurityService {
    String getRandomPassword();

    String getHash(String text);
}
