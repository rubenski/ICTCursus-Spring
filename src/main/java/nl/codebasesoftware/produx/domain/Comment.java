package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.CommentEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;

import javax.persistence.*;
import java.util.Date;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 21:32
 */
@Entity
public class Comment implements DomainEntity {

    private Long id;
    private String commentText;
    private String email;
    private String name;
    private Date date;
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    @Transient
    public CommentEntityDTO toDTO(){
        CommentEntityDTO dto = new CommentEntityDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setCommentText(commentText);
        dto.setCourse(course.toDTO());
        dto.setDate(date);
        return dto;
    }
}
