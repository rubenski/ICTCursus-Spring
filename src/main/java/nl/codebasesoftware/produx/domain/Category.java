package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.comparator.HighlightedCoursesEndDateDescending;
import nl.codebasesoftware.produx.comparator.NameComparable;
import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 22:08
 */
@Entity
public class Category implements DomainEntity, NameComparable {

    private Long id;
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private Category parent;
    private String urlTitle;
    private List<Category> children;
    private Set<HighlightedCourseOnCategory> highlightedCourses = new TreeSet<HighlightedCourseOnCategory>(new HighlightedCoursesEndDateDescending());

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @Column(unique = true, nullable = false)
    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "category", orphanRemoval = true)
    public Set<HighlightedCourseOnCategory> getHighlightedCourses() {
        return highlightedCourses;
    }

    public void setHighlightedCourses(Set<HighlightedCourseOnCategory> highlightedCourses) {
        this.highlightedCourses = highlightedCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return !(id != null ? !id.equals(category.id) : category.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public CategoryEntityDTO toDTO(){
        CategoryEntityDTO category = new CategoryEntityDTO();
        category.setId(id);
        category.setName(name);
        category.setUrlTitle(urlTitle);
        return category;
    }
}
