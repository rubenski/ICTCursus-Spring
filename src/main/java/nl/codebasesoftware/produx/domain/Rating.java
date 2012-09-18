package nl.codebasesoftware.produx.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 17:02
 */
public class Rating implements DomainObject {

    private Long id;
    private Integer version;
    private String message;
    private int rating;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
