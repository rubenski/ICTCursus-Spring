package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 22:50
 */
public class AccountRequestDecision {

    int decision = -1;
    long articleRequestId;

    public AccountRequestDecision() {
    }

    public AccountRequestDecision(long articleRequestId) {
        this.articleRequestId = articleRequestId;
    }

    public int getDecision() {
        return decision;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }

    public long getArticleRequestId() {
        return articleRequestId;
    }

    public void setArticleRequestId(long articleRequestId) {
        this.articleRequestId = articleRequestId;
    }
}
