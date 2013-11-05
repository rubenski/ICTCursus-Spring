package nl.codebasesoftware.produx.domain.dto.entity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 27-8-13
 * Time: 17:12
 */
public class CourseRequestEntityDTO extends DomainEntityDTO implements AsInvoiceRecord {

    private Long id;
    private int prefix;
    private String requesterName;
    private String email;
    private String message;
    private int numberOfParticipants;
    private CourseEntityDTO course;
    private String courseName;
    private String phone;
    private Calendar created;
    private boolean invalid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public CourseEntityDTO getCourse() {
        return course;
    }

    public void setCourse(CourseEntityDTO course) {
        this.course = course;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
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
    public String getInvoiceDescription(){
        return String.format("Aanvraag voor '%s' (ID: #%d)", courseName, id);
    }

    @Override
    public long getInvoicePriceInCents(){
        if(numberOfParticipants > 4){
            return course.getPrice() / 50;
        }
        return course.getPrice() / 100;
    }
}
