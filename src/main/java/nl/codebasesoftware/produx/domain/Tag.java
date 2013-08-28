package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.TagEntityDTO;

import javax.persistence.*;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 16-9-12
 * Time: 10:58
 */
@Entity
public class Tag implements DomainEntity {

    private Long id;
    private String name;
    private Set<Course> courses;


    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "tags")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Transient
    public TagEntityDTO toDTO(){
        TagEntityDTO tagEntityDTO = new TagEntityDTO();
        tagEntityDTO.setId(id);
        tagEntityDTO.setName(name);
        return tagEntityDTO;
    }
}
