package nl.codebasesoftware.produx.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 23:49
 */
public class InvoiceBatch implements DomainObject {

    private Long id;
    private Calendar date;
    private Calendar completed;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
