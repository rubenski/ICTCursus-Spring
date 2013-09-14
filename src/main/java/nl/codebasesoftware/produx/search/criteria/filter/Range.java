package nl.codebasesoftware.produx.search.criteria.filter;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-9-13
 * Time: 22:18
 * To change this template use File | Settings | File Templates.
 */
public class Range<L, R> {
    private L left;
    private R right;

    public Range(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }
}
