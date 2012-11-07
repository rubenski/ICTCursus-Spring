package nl.codebasesoftware.produx.domain.support;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Tag;

import java.util.List;

/**
 * User: rvanloen
 * Date: 26-9-12
 * Time: 23:50
 */
public class FacetContent {

    private List<Tag> tags;
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
