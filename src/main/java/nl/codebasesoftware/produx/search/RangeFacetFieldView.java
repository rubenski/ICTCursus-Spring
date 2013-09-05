package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 4-9-13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class RangeFacetFieldView extends FacetFieldView {

    private final Integer value;
    private final Integer gap;
    private String baseUrl;

    public RangeFacetFieldView(String fieldName, Integer value, long count, Integer gap, String baseUrl) {
        super(fieldName, count);
        this.value = value;
        this.gap = gap;
        this.baseUrl = baseUrl;
    }

    @Override
    public String getNameKey(){
        return String.format("rangefacet.%s.%d", fieldName, value);
    }

    @Override
    public String getUrlToken(){
        return String.format("%s/%s:%d-%d", baseUrl, fieldName, value, value + gap);
    }
}
