package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 4-5-13
 * Time: 0:49
 */
@Entity
public class HighlightedCourseOnCategory implements DomainObject {

    private Long id;
    private Calendar startTime;
    private Calendar endTime;
    private Category category;
    private Course course;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }
}
