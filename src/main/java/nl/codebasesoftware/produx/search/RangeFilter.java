package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class RangeFilter<L,R> {

    private final String field;
    private final L min;
    private final R max;

    public RangeFilter(String field, L min, R max) {
        this.field = field;
        this.min = min;
        this.max = max;
    }

    public String getField() {
        return field;
    }

    public L getMin() {
        return min;
    }

    public R getMax() {
        return max;
    }

    public String getUrlToken(){
        return String.format("/%s:%s-%s", field, min, max);
    }
}
