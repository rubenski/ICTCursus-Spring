package nl.codebasesoftware.produx.domain.dto.entity;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceRecordEntityDTO extends DomainEntityDTO {

    private Long id;
    private int price;
    private Calendar sourceRecordCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Calendar getSourceRecordCreated() {
        return sourceRecordCreated;
    }

    public void setSourceRecordCreated(Calendar sourceRecordCreated) {
        this.sourceRecordCreated = sourceRecordCreated;
    }
}
