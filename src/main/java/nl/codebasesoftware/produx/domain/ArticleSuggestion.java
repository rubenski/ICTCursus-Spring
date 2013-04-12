package nl.codebasesoftware.produx.domain;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 21:52
 */
@Entity
public class ArticleSuggestion implements DomainObject {

    private Long id;
    private String suggestedTitle;
    private String suggestionText;
    private Article article;
    private UserProfile suggester;


    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getSuggestedTitle() {
        return suggestedTitle;
    }

    public void setSuggestedTitle(String suggestedTitle) {
        this.suggestedTitle = suggestedTitle;
    }

    @Lob
    @Column(nullable = false)
    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    @OneToOne
    @JoinColumn(name="article_id", nullable = true)
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "suggested_by", nullable = false)
    public UserProfile getSuggester() {
        return suggester;
    }

    public void setSuggester(UserProfile suggester) {
        this.suggester = suggester;
    }
}
