package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.comparator.RankOrderable;
import nl.codebasesoftware.produx.domain.dto.entity.ArticlePageEntityDTO;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 11:09
 */
@Table(
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"pos", "article_id"})
)
@Entity
public class ArticlePage implements DomainEntity, RankOrderable {

    private Long id;
    private String text;
    private String title;
    private String description;
    private String keywords;
    private Integer position;
    private Article article;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    // TODO: this columnDefinition is untested. It might cause trouble, but it may fix the following problem:
    // http://stackoverflow.com/questions/10957238/incorrect-string-value-when-trying-to-insert-utf-8-into-mysql-via-jdbc
    // http://stackoverflow.com/questions/2108824/mysql-incorrect-string-value-error-when-save-unicode-string-in-django
    // columnDefinition = "CHARACTER SET utf8 COLLATE utf8_general_ci"
    @Column(nullable = false, name = "page_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    // This  must be nullable to allow nullifying when updating the article page's positions
    @Column(nullable = true, name = "pos")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    @Transient
    public int getDisplayRank() {
        return position;
    }

    @Override
    @Transient
    public ArticlePageEntityDTO toDTO() {
        ArticlePageEntityDTO dto = new ArticlePageEntityDTO();
        dto.setId(id);
        dto.setDescription(description);
        dto.setKeywords(keywords);
        dto.setPosition(position);
        dto.setText(text);
        dto.setTitle(title);
        dto.setArticle(article.toDTOWithoutPages());
        return dto;
    }
}
