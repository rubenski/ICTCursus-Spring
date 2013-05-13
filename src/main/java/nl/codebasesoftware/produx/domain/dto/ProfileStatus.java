package nl.codebasesoftware.produx.domain.dto;

/**
 * User: rvanloen
 * Date: 13-5-13
 * Time: 1:18
 */
public class ProfileStatus {
    private boolean enabled;
    private String name;

    public ProfileStatus(boolean enabled, String name) {
        this.name = name;
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }
}