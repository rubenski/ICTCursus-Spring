package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Course;

import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 2:25
 */
public class ClickEntityDTO extends DomainEntityDTO {

    private Long id;
    private CourseEntityDTO course;
    private String ip;
    private String userAgent;
    private Calendar time;
    private int commission;

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

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }
}
