package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Category;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.HighlightedCourseOnCategory;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public class HighlightedCourseInvoiceRecordEntityDTO extends DomainEntityDTO {

    private Long id;
    private int price;
    private String courseName;
    private Calendar sourceRecordCreated;
    private HighlightedCourseOnCategoryEntityDTO highlightedCourseOnCategory;
    private Calendar startTime;
    private Calendar endTime;
    private CategoryEntityDTO category;
    private CourseEntityDTO course;

    public HighlightedCourseOnCategoryEntityDTO getHighlightedCourseOnCategory() {
        return highlightedCourseOnCategory;
    }

    public void setHighlightedCourseOnCategory(HighlightedCourseOnCategoryEntityDTO highlightedCourseOnCategory) {
        this.highlightedCourseOnCategory = highlightedCourseOnCategory;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public CategoryEntityDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryEntityDTO category) {
        this.category = category;
    }

    public CourseEntityDTO getCourse() {
        return course;
    }

    public void setCourse(CourseEntityDTO course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Calendar getSourceRecordCreated() {
        return sourceRecordCreated;
    }

    public void setSourceRecordCreated(Calendar sourceRecordCreated) {
        this.sourceRecordCreated = sourceRecordCreated;
    }
}
