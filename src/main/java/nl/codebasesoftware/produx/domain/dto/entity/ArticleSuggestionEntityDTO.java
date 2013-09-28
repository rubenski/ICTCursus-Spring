package nl.codebasesoftware.produx.domain.dto.entity;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */
public class ArticleSuggestionEntityDTO extends DomainEntityDTO {

    private Long id;
    private String suggestedTitle;
    private String suggestionText;
    private ArticleEntityDTO article;
    private UserProfileEntityDTO suggester;
    private boolean approved;
    private Calendar created;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuggestedTitle() {
        return suggestedTitle;
    }

    public void setSuggestedTitle(String suggestedTitle) {
        this.suggestedTitle = suggestedTitle;
    }

    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public ArticleEntityDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleEntityDTO article) {
        this.article = article;
    }

    public UserProfileEntityDTO getSuggester() {
        return suggester;
    }

    public void setSuggester(UserProfileEntityDTO suggester) {
        this.suggester = suggester;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
