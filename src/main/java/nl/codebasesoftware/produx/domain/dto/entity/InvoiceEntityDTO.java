package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.InvoiceRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
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
    private int serialNumber;
    private String lastInvoiceNumber;
    private Set<InvoiceRecordEntityDTO> records = new HashSet<>();

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

    public void setRecords(Set<InvoiceRecordEntityDTO> records) {
        this.records = records;
    }

    public void addRecord(InvoiceRecordEntityDTO record) {
        this.records.add(record);
    }

    public String getCreationDatePretty(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String format = df.format(dateCreated.getTime());
        return format;
    }

    public String getInvoiceNumber(){
        String s = String.format("%s%04d", company.getCompanyPrefix(), serialNumber);
        return s;
    }
}