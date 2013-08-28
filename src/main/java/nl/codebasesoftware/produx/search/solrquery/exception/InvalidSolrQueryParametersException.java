package nl.codebasesoftware.produx.search.solrquery.exception;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-8-13
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public class InvalidSolrQueryParametersException extends Exception {
    public InvalidSolrQueryParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSolrQueryParametersException(String message) {
        super(message);
    }
}
