package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 10:58
 */
@Entity
public class Tag implements DomainObject {

    private Long id;
    private Integer version;
    private String name;
    private String description;
    private Set<Course> courses;


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

    @ManyToMany(mappedBy = "tags")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
