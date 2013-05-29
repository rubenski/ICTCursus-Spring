package nl.codebasesoftware.produx.formdata;

import nl.codebasesoftware.produx.domain.ArticlePage;

import java.util.List;

/**
 * User: rvanloen
 * Date: 8-4-13
 * Time: 23:54
 */
public class EditArticleFormData extends AddArticleFormData {

    private boolean published;
    private List<ArticlePage> pages;
    private List<String> pageOrder;
    private String text;

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public List<ArticlePage> getPages() {
        return pages;
    }

    public void setPages(List<ArticlePage> pages) {
        this.pages = pages;
    }

    public List<String> getPageOrder() {
        return pageOrder;
    }

    public void setPageOrder(List<String> pageOrder) {
        this.pageOrder = pageOrder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
