package nl.codebasesoftware.produx.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 11:47
 */
@Entity
public class Page implements DomainObject {

    private Long id;
    private String body;
    private String title;
    private String description;
    private String keywords;
    private boolean displayInMenu;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
