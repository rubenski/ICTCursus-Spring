package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Invoice;
import nl.codebasesoftware.produx.domain.support.InvoiceSentStatus;

import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 20:52
 */
public class SentInvoiceEntityDTO extends DomainEntityDTO {

    private Long id;
    private Invoice invoice;
    private Calendar timeSent;
    private InvoiceSentStatus status;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}