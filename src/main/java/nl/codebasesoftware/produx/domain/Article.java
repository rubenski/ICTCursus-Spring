package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.service.business.ArticleUrl;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
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
    private Set<ArticlePage> pages = new HashSet<ArticlePage>();
    private Calendar creationDate;
    private Calendar firstPublicationDate;
    private boolean published;
    private UserProfile author;
    private String text;
    private Category category;

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
    @OrderBy("position ASC")
    public Set<ArticlePage> getPages() {
        return pages;
    }

    public void setPages(Set<ArticlePage> pages) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userprofile_id")
    public UserProfile getAuthor() {
        return author;
    }

    public void setAuthor(UserProfile author) {
        this.author = author;
    }

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Lob
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Transient
    public int getNextPageNumber() {
        return pages.size() + 1;
    }

    @Transient
    public String getUrl(){
        return ArticleUrl.createArticleUrl(id, category.getName(), title);
    }


    @Transient
    public boolean hasPages(){
        return pages.size() > 0;
    }

    @Transient
    public ArticlePage getArticlePage(int position){
        for (ArticlePage page : pages) {
            if(page.getPosition().equals(position)){
                return page;
            }
        }

        return null;
    }
}
