package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.HighlightedCourseOnCategoryEntityDTO;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 4-5-13
 * Time: 0:49
 */
@Entity
public class HighlightedCoursePeriod implements DomainEntity {

    private Long id;
    private Calendar startTime;
    private Calendar endTime;
    private Category category;
    private Course course;
    private Calendar created;

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

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override
    public HighlightedCourseOnCategoryEntityDTO toDTO() {
        HighlightedCourseOnCategoryEntityDTO dto = new HighlightedCourseOnCategoryEntityDTO();
        dto.setId(id);
        dto.setCategory(category.toDTO());
        // dto.setCourse(course.toDTO());
        dto.setCreated(created);
        dto.setEndTime(endTime);
        dto.setStartTime(startTime);
        return dto;
    }
}
