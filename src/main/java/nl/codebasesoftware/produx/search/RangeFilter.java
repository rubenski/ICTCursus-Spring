package nl.codebasesoftware.produx.search;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 10-8-13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public interface RangeFilter<T> {
    T getMin();
    T getMax();
}
