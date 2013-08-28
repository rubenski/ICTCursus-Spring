package nl.codebasesoftware.produx.domain.dto.entity;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceBatchEntityDTO extends DomainEntityDTO {

    private Long id;
    private Calendar date;
    private Calendar completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getCompleted() {
        return completed;
    }

    public void setCompleted(Calendar completed) {
        this.completed = completed;
    }
}
