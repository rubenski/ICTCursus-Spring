package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 4-9-13
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class FacetFilterLink {

    protected final String fieldName;
    protected final long count;

    protected FacetFilterLink(String fieldName, long count) {
        this.fieldName = fieldName;
        this.count = count;
    }

    public  Long getCount(){
        return count;
    }

    protected abstract String getNameKey();
    protected abstract String getUrlToken();

}
