package nl.codebasesoftware.produx.domain.optionlists;

/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 22:41
 */
public enum Prefixes {
    SIR(0, "Dhr"),
    MADAM(1, "Mevr");

    private final String prefix;
    private final int id;

    Prefixes(int id, String prefix) {
        this.prefix = prefix;
        this.id = id;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public int getId() {
        return this.id;
    }
}
