package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 4-3-13
 * Time: 23:27
 */
@Entity
public class Article implements DomainObject {

    private Long id;
    private String teaser;
    private String title;
    private Set<ArticlePage> articlePages;
    private String publicationDate;
    private UserProfile author;


    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "article")
    public Set<ArticlePage> getArticlePages() {
        return articlePages;
    }

    public void setArticlePages(Set<ArticlePage> articlePages) {
        this.articlePages = articlePages;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    public UserProfile getAuthor() {
        return author;
    }

    public void setAuthor(UserProfile author) {
        this.author = author;
    }
}
