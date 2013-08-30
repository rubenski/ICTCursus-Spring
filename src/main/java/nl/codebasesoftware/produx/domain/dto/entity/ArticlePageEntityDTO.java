package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.service.business.ArticlePageUrl;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class ArticlePageEntityDTO extends DomainEntityDTO {

    private Long id;
    private String text;
    private String title;
    private String description;
    private String keywords;
    private Integer position;
    private ArticleEntityDTO article;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public ArticleEntityDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleEntityDTO article) {
        this.article = article;
    }

    // Utility methods

    public String getUrl() {
        return ArticlePageUrl.create(article.getId(), article.getCategory().getName(), position, title);
    }


}
