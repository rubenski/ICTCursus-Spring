package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 8-4-13
 * Time: 1:33
 */
public class AddArticleFormData {

    private Long id;
    private String teaser;
    private String title;
    private boolean published;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
