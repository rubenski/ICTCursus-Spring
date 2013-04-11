package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.Calendar;
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
    private Set<ArticlePage> pages;
    private Calendar creationDate;
    private Calendar firstPublicationDate;
    private boolean published;
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
        return pages;
    }

    public void setArticlePages(Set<ArticlePage> pages) {
        this.pages = pages;
    }

    @Lob
    @Column(nullable = false)
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

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getFirstPublicationDate() {
        return firstPublicationDate;
    }

    public void setFirstPublicationDate(Calendar firstPublicationDate) {
        this.firstPublicationDate = firstPublicationDate;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userprofile_id")
    public UserProfile getAuthor() {
        return author;
    }

    public void setAuthor(UserProfile author) {
        this.author = author;
    }
}
