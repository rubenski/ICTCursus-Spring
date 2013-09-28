package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 11-4-13
 * Time: 21:47
 */
public class ArticlePageFormData {

    private Long id;
    private Long articleId;
    private String title;
    private String text;
    private String metaKeywords;
    private String metaDescription;
    private int position;
    private String remove;
    private boolean articleTitleEnabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }

    public boolean removeClicked() {
        return this.remove != null && this.remove.equals("Verwijderen");
    }

    public void setArticleTitleEnabled(boolean b) {
        this.articleTitleEnabled = b;
    }

    public boolean isArticleTitleEnabled() {
        return articleTitleEnabled;
    }
}
