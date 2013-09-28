package nl.codebasesoftware.produx.search.result;

/**
 * User: rvanloen
 * Date: 19-9-13
 * Time: 22:02
 */
public interface RangeFilterLinkStrategy {
    String createLabel(String value, Object gap);

    String createUrlToken(String field, String value, Object gap);
}
