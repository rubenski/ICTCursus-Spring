package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 25-4-13
 * Time: 16:45
 */
@Entity
public class CourseRequest implements DomainEntity {

    private Long id;
    private int prefix;
    private String requesterName;
    private String email;
    private String message;
    private String phone;
    private int numberOfParticipants;
    private Course course;
    private String courseName;
    private Calendar created;
    private boolean invalid;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    @Lob
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(nullable = false)
    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    @Column(nullable = false)
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    @Column(nullable = false)
    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(nullable = false)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    @Transient
    public CourseRequestEntityDTO toDTO() {
        CourseRequestEntityDTO dto = new CourseRequestEntityDTO();
        dto.setId(id);
        dto.setRequesterName(requesterName);
        dto.setPrefix(prefix);
        dto.setCourse(course.toDTO());
        dto.setCourseName(courseName);
        dto.setCreated(created);
        dto.setEmail(email);
        dto.setInvalid(invalid);
        dto.setPhone(phone);
        dto.setMessage(message);
        dto.setNumberOfParticipants(numberOfParticipants);
        dto.setInvalid(invalid);
        return dto;
    }
}
