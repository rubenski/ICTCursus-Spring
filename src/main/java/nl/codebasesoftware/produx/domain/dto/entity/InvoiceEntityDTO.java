package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.InvoiceRecord;

import java.util.Calendar;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceEntityDTO extends DomainEntityDTO {

    private Long id;
    private Calendar dateCreated;
    private CompanyEntityDTO company;
    private Set<InvoiceRecordEntityDTO> records;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public CompanyEntityDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyEntityDTO company) {
        this.company = company;
    }

    public Set<InvoiceRecordEntityDTO> getRecords() {
        return records;
    }

    public void addRecord(InvoiceRecordEntityDTO record) {
        this.records.add(record);
    }
}
