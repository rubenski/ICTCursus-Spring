package nl.codebasesoftware.produx.formdata;

import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 21:10
 */
public class ArticleSuggestionFormData {

    private Long id;
    private Long articleId;
    private String title;
    private String description;
    private Calendar created;
    private String email;
    private long suggesterId;
    private boolean approved;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public long getSuggesterId() {
        return suggesterId;
    }

    public void setSuggesterId(long suggesterId) {
        this.suggesterId = suggesterId;
    }

    public boolean isUsed(){
        return articleId != null;
    }
}
