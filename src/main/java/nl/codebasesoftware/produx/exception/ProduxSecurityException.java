package nl.codebasesoftware.produx.exception;

/**
 * User: rvanloen
 * Date: 3-4-13
 * Time: 22:46
 */
public class ProduxSecurityException extends ProduxServiceException {

    public ProduxSecurityException() {
    }

    public ProduxSecurityException(String message) {
        super(message);
    }
}
