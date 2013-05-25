package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.comparator.RankOrderable;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 11:09
 */
@Table(
    uniqueConstraints=
        @UniqueConstraint(columnNames={"position", "article_id"})
)
@Entity
public class ArticlePage implements DomainObject, RankOrderable {

    private Long id;
    private String body;
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
    @Column(nullable = false)
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

    // This field must be nullable to allow nullifying when updating the article page's positions
    @Column(nullable = true, name="position")
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
}
