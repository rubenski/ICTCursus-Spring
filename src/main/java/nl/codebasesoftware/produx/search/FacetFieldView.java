package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 4-9-13
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class FacetFieldView {

    protected final String fieldName;
    protected final long count;
    protected final String baseUrl;

    protected FacetFieldView(String fieldName, long count, String baseUrl) {
        this.fieldName = fieldName;
        this.count = count;
        this.baseUrl = baseUrl;
    }

    public  Long getCount(){
        return count;
    }

    protected abstract String getNameKey();
    protected abstract String getUrl();

}
