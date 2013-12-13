package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.InvoiceBatchEntityDTO;

import javax.persistence.*;
import java.util.*;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 20:32
 */
@Entity
public class InvoiceBatch implements DomainEntity {

    private Long id;
    private Calendar jobStarted;
    private Calendar jobCompleted;
    private int month;
    private int year;
    private Set<InvoiceProcessingAttempt> invoiceProcessingAttempts = new HashSet<>();

    @Override
    @Id
    @GeneratedValue
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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "batch")
    public Set<InvoiceProcessingAttempt> getInvoiceProcessingAttempts() {
        return invoiceProcessingAttempts;
    }

    public void setInvoiceProcessingAttempts(Set<InvoiceProcessingAttempt> invoiceProcessingAttempts) {
        this.invoiceProcessingAttempts = invoiceProcessingAttempts;
    }

    public void addInvoiceProcessingAttempt(InvoiceProcessingAttempt invoiceProcessingAttempt){
        invoiceProcessingAttempts.add(invoiceProcessingAttempt);
    }

    @Column(nullable = false)
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Column(nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public InvoiceBatchEntityDTO toDTO() {
        return null;
    }
}
