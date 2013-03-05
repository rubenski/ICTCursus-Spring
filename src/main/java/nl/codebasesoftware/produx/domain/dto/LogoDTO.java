package nl.codebasesoftware.produx.domain.dto;

/**
 * User: rvanloen
 * Date: 4-3-13
 * Time: 22:37
 */
public class LogoDTO {

    String base64String;

    public LogoDTO(String base64String) {
        this.base64String = base64String;
    }

    public String getBase64String() {
        return base64String;
    }
}
