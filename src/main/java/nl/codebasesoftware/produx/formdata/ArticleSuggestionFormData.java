package nl.codebasesoftware.produx.formdata;

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
}
