package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.CourseDateEntityDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 18:27
 */
@Entity
public class CourseDate implements DomainEntity {

    private Long id;
    private Calendar startDate;
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

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseDateEntityDTO toDTO() {
        CourseDateEntityDTO dto = new CourseDateEntityDTO();
        dto.setId(id);
        dto.setStartDate(startDate);
        return dto;
    }


}
