package nl.codebasesoftware.produx.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 18:27
 */
@Entity
public class CourseDate implements DomainObject {

    private Long id;
    private Calendar startDate;

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
}
