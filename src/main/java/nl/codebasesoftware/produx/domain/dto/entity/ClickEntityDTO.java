package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Course;

import javax.persistence.Transient;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 2:25
 */
public class ClickEntityDTO extends DomainEntityDTO implements InvoiceItem {

    private Long id;
    private CourseEntityDTO course;
    private String ip;
    private String userAgent;
    private Calendar created;
    private int invoicePriceInCents;
    private String externalUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseEntityDTO getCourse() {
        return course;
    }

    public void setCourse(CourseEntityDTO course) {
        this.course = course;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override
    public int getInvoicePriceInCents() {
        return invoicePriceInCents;
    }

    public void setInvoicePriceInCents(int invoicePriceInCents) {
        this.invoicePriceInCents = invoicePriceInCents;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    @Override
    public String getInvoiceDescription() {
        return String.format("Klik vanaf cursus '%s'", course.getName());
    }
}
