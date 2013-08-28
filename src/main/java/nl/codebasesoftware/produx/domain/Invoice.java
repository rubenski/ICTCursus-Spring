package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceRecordEntityDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 23:31
 */
public class Invoice implements DomainEntity {

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

    @Transient
    @Override
    public InvoiceEntityDTO toDTO(){
        InvoiceEntityDTO dto = new InvoiceEntityDTO();
        dto.setId(id);
        dto.setCompany(company.toDTO());
        dto.setDateCreated(dateCreated);
        return dto;
    }

}
