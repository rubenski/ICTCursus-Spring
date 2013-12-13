package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 2:24
 */
@SuppressWarnings("unchecked")
@Entity
public class Click implements DomainEntity, CostItem {

    private Long id;
    private Course course;
    private String ip;
    private String userAgent;
    private Calendar time;
    private int commission;
    private String externalUrl;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(nullable = false)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(nullable = false)
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    @Column(nullable = false)
    @Override
    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    @Column(nullable = false)
    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    @Transient
    public ClickEntityDTO toDTO(){
        ClickEntityDTO dto =  new ClickEntityDTO();
        dto.setId(id);
        dto.setCommission(commission);
        dto.setCourse(course.toDTO());
        dto.setIp(ip);
        dto.setTime(time);
        dto.setUserAgent(userAgent);
        return dto;
    }
}
