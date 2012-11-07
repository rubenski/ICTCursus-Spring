package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
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
    private ArticlePage parentPage;
    private Integer position;
    private Set<ArticlePage> childPages;
    private Set<Comment> comments;


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

    @OneToMany
    @JoinColumn(name = "parentPage_id")
    public Set<ArticlePage> getChildPages() {
        return childPages;
    }

    public void setChildPages(Set<ArticlePage> childPages) {
        this.childPages = childPages;
    }

    @ManyToOne
    public ArticlePage getParentPage() {
        return parentPage;
    }

    public void setParentPage(ArticlePage parentPage) {
        this.parentPage = parentPage;
    }


    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = { javax.persistence.CascadeType.ALL })
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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
