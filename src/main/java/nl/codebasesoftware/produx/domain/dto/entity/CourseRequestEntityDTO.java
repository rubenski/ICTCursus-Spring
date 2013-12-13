package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Course;

import java.text.NumberFormat;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 27-8-13
 * Time: 17:12
 */
public class CourseRequestEntityDTO extends DomainEntityDTO implements InvoiceItem {

    private Long id;
    private int prefix;
    private String requesterName;
    private String email;
    private String message;
    private String phone;
    private int numberOfParticipants;
    private CourseEntityDTO course;
    private String courseName;
    private String company;
    private Calendar created;
    private boolean invalid;
    private double currentCommissionPercentage;
    private long currentCoursePriceInCents;
    private int invoicePriceInCents;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

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

    public double getCurrentCommissionPercentage() {
        return currentCommissionPercentage;
    }

    public void setCurrentCommissionPercentage(double currentCommissionPercentage) {
        this.currentCommissionPercentage = currentCommissionPercentage;
    }

    public long getCurrentCoursePriceInCents() {
        return currentCoursePriceInCents;
    }

    public void setCurrentCoursePriceInCents(long currentCoursePriceInCents) {
        this.currentCoursePriceInCents = currentCoursePriceInCents;
    }

    public int getInvoicePriceInCents() {
        return invoicePriceInCents;
    }

    public void setInvoicePriceInCents(int invoicePriceInCents) {
        this.invoicePriceInCents = invoicePriceInCents;
    }

    @Override
    public String getInvoiceDescription(){

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);
        String percentage = nf.format(currentCommissionPercentage);

        return String.format("Aanvraag voor '%s' (ID: #%d) van %s tegen %s%%", courseName, id, requesterName, percentage);
    }


}
