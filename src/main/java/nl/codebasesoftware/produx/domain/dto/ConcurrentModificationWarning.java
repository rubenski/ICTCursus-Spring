package nl.codebasesoftware.produx.domain.dto;

/**
 * User: rvanloen
 * Date: 26-4-13
 * Time: 1:10
 */
public class ConcurrentModificationWarning {

    String user;
    String warning;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
