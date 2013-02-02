package nl.codebasesoftware.produx.service.helpers;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Region;
import nl.codebasesoftware.produx.domain.Tag;

import java.util.HashSet;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 12:49
 */
public class CourseFilter {
    private Set<Category> categories = new HashSet<Category>();
    private Set<Tag> tags = new HashSet<Tag>();
    private Set<Region> regions = new HashSet<Region>();
    private String freeTextQuery; // TODO: to be implemented later with Hibernate nl.codebasesoftware.produx.search

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category){
        this.categories.add(category);
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

    public String getFreeTextQuery() {
        return freeTextQuery;
    }

    public void setFreeTextQuery(String freeTextQuery) {
        this.freeTextQuery = freeTextQuery;
    }
}
