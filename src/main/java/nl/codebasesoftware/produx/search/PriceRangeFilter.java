package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 9-8-13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class PriceRangeFilter implements RangeFilter<Integer> {

    private final int min;
    private final int max;

    public PriceRangeFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }
}
