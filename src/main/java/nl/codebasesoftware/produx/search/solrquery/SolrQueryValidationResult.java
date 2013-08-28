package nl.codebasesoftware.produx.search.solrquery;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 31-7-13
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
public class SolrQueryValidationResult {

    // Initialize to valid
    private boolean valid = true;
    private String message;


    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
