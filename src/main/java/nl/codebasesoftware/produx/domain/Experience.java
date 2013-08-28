package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.ExperienceEntityDTO;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 18-9-12
 * Time: 17:02
 */
@Entity
public class Experience implements DomainEntity {

    private Long id;
    private String message;
    private int rating;
    private Course course;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
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

    @Override
    public ExperienceEntityDTO toDTO() {
        ExperienceEntityDTO dto = new ExperienceEntityDTO();
        dto.setId(id);
        dto.setMessage(message);
        dto.setRating(rating);
        return dto;
    }
}
