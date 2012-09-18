package nl.codebasesoftware.produx.domain;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 17:02
 */
@Entity
public class Experience implements DomainObject {

    private Long id;
    private Integer version;
    private String message;
    private int rating;
    private Course course;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
