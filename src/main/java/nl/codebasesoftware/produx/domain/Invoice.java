package nl.codebasesoftware.produx.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 23:31
 */
public class Invoice implements DomainObject {

    private Long id;
    private Calendar dateCreated;
    private Company company;
    private Set<InvoiceRecord> records;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<InvoiceRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<InvoiceRecord> records) {
        this.records = records;
    }
}
