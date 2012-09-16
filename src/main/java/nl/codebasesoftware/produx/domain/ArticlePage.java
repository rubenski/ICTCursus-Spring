package nl.codebasesoftware.produx.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 11:09
 */
@Entity
public class ArticlePage implements DomainObject {

    private Long id;
    private Integer version;
    private String body;
    private String title;
    private String publicationDate;
    private String description;
    private String keywords;
    private ArticlePage parent;
    private Integer position;
    private Set<ArticlePage> pages;

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

    public Set<ArticlePage> getPages() {
        return pages;
    }

    public void setPages(Set<ArticlePage> pages) {
        this.pages = pages;
    }

    public ArticlePage getParent() {
        return parent;
    }

    public void setParent(ArticlePage parent) {
        this.parent = parent;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
