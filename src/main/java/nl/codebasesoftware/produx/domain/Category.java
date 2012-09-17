package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 22:08
 */
@Entity
public class Category implements DomainObject {

    private Long id;
    private Integer version;
    private String name;
    private String description;
    private Set<Course> courses = new HashSet<Course>();
    private Category parent;
    private Long numberOfVisits;
    private String urlTitle;
    private Set<Category> children;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique=true, nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "category")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @ManyToOne
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Long getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(Long numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    @Column(unique = true, nullable = false)
    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    @OneToMany
    @JoinColumn(name = "parent_id")
    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }
}
