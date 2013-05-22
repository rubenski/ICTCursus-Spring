package nl.codebasesoftware.produx.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 21-5-13
 * Time: 0:23
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class HighlightedCourseInvoiceRecord extends InvoiceRecord {

    private HighlightedCourseOnCategory highlightedCourseOnCategory;
    private Calendar startTime;
    private Calendar endTime;
    private Category category;
    private Course course;

    @ManyToOne
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    @ManyToOne
    public HighlightedCourseOnCategory getHighlightedCourseOnCategory() {
        return highlightedCourseOnCategory;
    }

    public void setHighlightedCourseOnCategory(HighlightedCourseOnCategory highlightedCourseOnCategory) {
        this.highlightedCourseOnCategory = highlightedCourseOnCategory;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }
}
