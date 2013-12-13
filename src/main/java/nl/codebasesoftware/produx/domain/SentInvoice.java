package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.SentInvoiceEntityDTO;
import nl.codebasesoftware.produx.domain.support.InvoiceSentStatus;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 20:36
 */
@Entity
public class SentInvoice implements DomainEntity {

    private Long id;
    private Invoice invoice;
    private Calendar timeSent;
    private InvoiceSentStatus status;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Calendar getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Calendar timeSent) {
        this.timeSent = timeSent;
    }

    public InvoiceSentStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceSentStatus status) {
        this.status = status;
    }

    @Override
    public SentInvoiceEntityDTO toDTO() {
        return new SentInvoiceEntityDTO();
    }
}
