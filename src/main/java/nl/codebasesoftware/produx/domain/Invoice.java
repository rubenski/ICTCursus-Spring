package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 23:31
 */
@Entity
public class Invoice implements DomainEntity {

    private Long id;
    private Calendar dateCreated;
    private Company company;
    private int serialNumber;
    private String lastInvoiceNumber;
    private Set<InvoiceRecord> records = new HashSet<>();

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
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

    @OneToMany(cascade = CascadeType.ALL)
    public Set<InvoiceRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<InvoiceRecord> records) {
        this.records = records;
    }

    public void addInvoiceRecord(InvoiceRecord invoiceRecord){
        this.records.add(invoiceRecord);
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLastInvoiceNumber() {
        return lastInvoiceNumber;
    }

    public void setLastInvoiceNumber(String lastInvoiceNumber) {
        this.lastInvoiceNumber = lastInvoiceNumber;
    }



    @Override
    public InvoiceEntityDTO toDTO() {
        InvoiceEntityDTO dto = new InvoiceEntityDTO();
        dto.setId(id);
        dto.setCompany(company.toDTO());
        dto.setDateCreated(dateCreated);
        dto.setSerialNumber(serialNumber);
        dto.setLastInvoiceNumber(lastInvoiceNumber);

        for (InvoiceRecord record : records) {
            dto.addRecord(record.toDTO());
        }

        return dto;
    }

    @Transient
    public String getNextInvoiceNumber(){
        return String.format("%s%d", company.getCompanyPrefix(), serialNumber);
    }

    @Transient
    public int getNextSerialNumber(){
        return serialNumber + 1;
    }

}
