package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.InvoiceProcessingAttempt;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceBatchEntityDTO extends DomainEntityDTO {

    private Long id;
    private Calendar jobStarted;
    private Calendar jobCompleted;
    private int month;
    private int year;
    private Set<InvoiceProcessingAttemptEntityDTO> invoiceProcessingAttempts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getJobStarted() {
        return jobStarted;
    }

    public void setJobStarted(Calendar jobStarted) {
        this.jobStarted = jobStarted;
    }

    public Calendar getJobCompleted() {
        return jobCompleted;
    }

    public void setJobCompleted(Calendar jobCompleted) {
        this.jobCompleted = jobCompleted;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<InvoiceProcessingAttemptEntityDTO> getInvoiceProcessingAttempts() {
        return invoiceProcessingAttempts;
    }

    public void setInvoiceProcessingAttempts(Set<InvoiceProcessingAttemptEntityDTO> invoiceProcessingAttempts) {
        this.invoiceProcessingAttempts = invoiceProcessingAttempts;
    }
}
