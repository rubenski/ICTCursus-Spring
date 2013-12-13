package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.Invoice;
import nl.codebasesoftware.produx.domain.InvoiceBatch;
import nl.codebasesoftware.produx.domain.support.InvoiceProcessingAttemptStatus;

import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 23:41
 */
public class InvoiceProcessingAttemptEntityDTO extends DomainEntityDTO {

    private Long id;
    private InvoiceEntityDTO invoice;
    private Calendar timeSent;
    private InvoiceProcessingAttemptStatus status;
    private String exceptionStackTrace;
    private InvoiceBatchEntityDTO batch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InvoiceEntityDTO getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntityDTO invoice) {
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

    public String getExceptionStackTrace() {
        return exceptionStackTrace;
    }

    public void setExceptionStackTrace(String exceptionStackTrace) {
        this.exceptionStackTrace = exceptionStackTrace;
    }

    public InvoiceBatchEntityDTO getBatch() {
        return batch;
    }

    public void setBatch(InvoiceBatchEntityDTO batch) {
        this.batch = batch;
    }
}
