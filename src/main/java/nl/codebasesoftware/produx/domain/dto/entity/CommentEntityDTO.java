package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Course;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class CommentEntityDTO extends DomainEntityDTO {

    private Long id;
    private String commentText;
    private String email;
    private String name;
    private Date date;
    private CourseEntityDTO course;

    public Long getId() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CourseEntityDTO getCourse() {
        return course;
    }

    public void setCourse(CourseEntityDTO course) {
        this.course = course;
    }
}
