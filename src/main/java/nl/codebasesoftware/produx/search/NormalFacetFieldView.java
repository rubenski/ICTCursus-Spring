package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 3-9-13
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class NormalFacetFieldView extends FacetFieldView {

    private final String value;

    public NormalFacetFieldView(String fieldName, String value, long count, String baseUrl) {
        super(fieldName, count);
        this.value = value;
    }

    @Override
    public String getNameKey(){
        return String.format("normalfacet.%s.%d", fieldName, value);
    }

    @Override
    public String getUrlToken(){
        return String.format("%s/%s:%s", fieldName, value);
    }
}
