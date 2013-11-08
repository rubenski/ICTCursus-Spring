package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.business.url.ArticleUrl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */
public class ArticleEntityDTO extends DomainEntityDTO {

    private Long id;
    private String teaser;
    private String title;
    private List<ArticlePageEntityDTO> pages = new ArrayList<>();
    private Calendar creationDate;
    private Calendar firstPublicationDate;
    private boolean published;
    private UserProfile author;
    private String text;
    private CategoryEntityDTO category;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

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

    public UserProfile getAuthor() {
        return author;
    }

    public void setAuthor(UserProfile author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ArticlePageEntityDTO> getPages() {
        return pages;
    }

    public void setPages(List<ArticlePageEntityDTO> pages) {
        this.pages = pages;
    }

    public CategoryEntityDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryEntityDTO category) {
        this.category = category;
    }

    public void addPage(ArticlePageEntityDTO page) {
        pages.add(page);
    }

    // Utility methods

    public String getUrl() {
        return ArticleUrl.createArticleUrl(id, category.getName(), title);
    }

    public ArticlePageEntityDTO getArticlePage(int position) {
        for (ArticlePageEntityDTO page : pages) {
            if (page.getPosition().equals(position)) {
                return page;
            }
        }
        return null;
    }
}
