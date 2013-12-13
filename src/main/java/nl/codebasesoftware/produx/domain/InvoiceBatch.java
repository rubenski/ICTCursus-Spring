package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceBatchEntityDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private Set<SentInvoice> sentInvoices = new HashSet<>();

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

    @OneToMany
    public Set<SentInvoice> getSentInvoices() {
        return sentInvoices;
    }

    public void setSentInvoices(Set<SentInvoice> sentInvoices) {
        this.sentInvoices = sentInvoices;
    }

    @Override
    public InvoiceBatchEntityDTO toDTO() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
