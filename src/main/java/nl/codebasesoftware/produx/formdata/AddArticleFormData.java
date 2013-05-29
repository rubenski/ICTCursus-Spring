package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 8-4-13
 * Time: 1:33
 */
public class AddArticleFormData {

    private Long id;
    private Long suggestionId;
    private String teaser;
    private String title;
    private boolean published;
    private Long category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(Long suggestionId) {
        this.suggestionId = suggestionId;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
