package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.ArticleSuggestionEntityDTO;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 21:52
 */
@Entity
public class ArticleSuggestion implements DomainEntity {

    private Long id;
    private String suggestedTitle;
    private String suggestionText;
    private Article article;
    private UserProfile suggester;
    private boolean approved;
    private Calendar created;
    private String email;


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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", nullable = true)
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "suggested_by", nullable = false)
    public UserProfile getSuggester() {
        return suggester;
    }

    public void setSuggester(UserProfile suggester) {
        this.suggester = suggester;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public boolean isUsed() {
        return article != null;
    }

    @Transient
    @Override
    public ArticleSuggestionEntityDTO toDTO() {
        ArticleSuggestionEntityDTO dto = new ArticleSuggestionEntityDTO();
        dto.setApproved(approved);
        dto.setArticle(article.toDTO());
        dto.setCreated(created);
        dto.setEmail(email);
        dto.setId(id);
        dto.setSuggestedTitle(suggestedTitle);
        dto.setSuggestionText(suggestionText);
        dto.setSuggester(suggester.toDTO());
        return dto;
    }
}
