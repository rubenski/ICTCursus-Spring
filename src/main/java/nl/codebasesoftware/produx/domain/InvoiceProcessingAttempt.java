package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.InvoiceProcessingAttemptEntityDTO;
import nl.codebasesoftware.produx.domain.support.InvoiceProcessingAttemptStatus;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 20:36
 */
@Entity
public class InvoiceProcessingAttempt implements DomainEntity {

    private Long id;
    private Invoice invoice;
    private Calendar timeSent;
    private InvoiceProcessingAttemptStatus status;
    private String exceptionStackTrace;
    private InvoiceBatch batch;

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

    public InvoiceProcessingAttemptStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceProcessingAttemptStatus status) {
        this.status = status;
    }

    @Override
    public InvoiceProcessingAttemptEntityDTO toDTO() {
        InvoiceProcessingAttemptEntityDTO attempt =  new InvoiceProcessingAttemptEntityDTO();
        attempt.setId(id);
        attempt.setExceptionStackTrace(exceptionStackTrace);
        attempt.setInvoice(invoice.toDTO());
        attempt.setStatus(status);
        attempt.setTimeSent(timeSent);
        return attempt;
    }

    @Lob
    public String getExceptionStackTrace() {
        return exceptionStackTrace;
    }

    public void setExceptionStackTrace(String error) {
        this.exceptionStackTrace = error;
    }

    @ManyToOne
    public InvoiceBatch getBatch() {
        return batch;
    }

    public void setBatch(InvoiceBatch batch) {
        this.batch = batch;
    }
}
