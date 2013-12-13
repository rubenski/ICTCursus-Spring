package nl.codebasesoftware.produx.domain;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 28-8-13
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 */
public enum InvoiceRecordType {

    COURSE_REQUEST("COURSE_REQUEST"),
    CLICK_TO_EXTERNAL_SITE("CLICK_TO_EXTERNAL_SITE");


    private InvoiceRecordType(final String type) {
        this.type = type;
    }

    private final String type;

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return type;
    }

}
