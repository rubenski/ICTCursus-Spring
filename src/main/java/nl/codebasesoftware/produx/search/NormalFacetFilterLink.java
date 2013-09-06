package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-9-13
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class NormalFacetFilterLink extends FacetFilterLink {

    private final String value;
    private String baseUrl;

    public NormalFacetFilterLink(String fieldName, String value, long count, String baseUrl) {
        super(fieldName, count);
        this.value = value;
        this.baseUrl = baseUrl;
    }

    @Override
    public String getNameKey(){
        return String.format("normalfacet.%s.%d", fieldName, value);
    }

    @Override
    public String getUrlToken(){
        return String.format("%s/%s:%s", baseUrl, fieldName, value);
    }
}
