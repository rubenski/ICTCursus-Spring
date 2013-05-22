package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 23:48
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InvoiceRecord implements DomainObject {

    private Long id;
    private int price;
    private String courseName;
    private Calendar sourceRecordCreated;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Column(nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Calendar getSourceRecordCreated() {
        return sourceRecordCreated;
    }

    public void setSourceRecordCreated(Calendar sourceRecordCreated) {
        this.sourceRecordCreated = sourceRecordCreated;
    }
}
