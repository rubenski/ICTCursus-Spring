package nl.codebasesoftware.produx.service.helpers;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Region;
import nl.codebasesoftware.produx.domain.Tag;

import java.util.Set;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 12:49
 */
public class CourseFilter {
    private Set<Category> categories;
    private Set<Tag> tags;
    private Set<Region> regions;

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
