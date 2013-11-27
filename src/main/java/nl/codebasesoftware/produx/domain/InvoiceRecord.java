package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.InvoiceRecordEntityDTO;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 23:48
 */
@Entity
public class InvoiceRecord implements DomainEntity {

    private Long id;
    private long priceInCents;
    private Calendar sourceRecordCreated;
    private String description;
    private Map<String, String> sourceFields = new HashMap<>();
    private InvoiceRecordType type;
    private Invoice invoice;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "invoicerecord_sourcefields", joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "fieldname")
    @Column(name = "value")
    public Map<String, String> getSourceFields() {
        return sourceFields;
    }

    public void setSourceFields(Map<String, String> sourceFields) {
        this.sourceFields = sourceFields;
    }

    @Column(nullable = false)
    public long getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(long price) {
        this.priceInCents = price;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Calendar getSourceRecordCreated() {
        return sourceRecordCreated;
    }

    public void setSourceRecordCreated(Calendar sourceRecordCreated) {
        this.sourceRecordCreated = sourceRecordCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "record_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public InvoiceRecordType getType() {
        return type;
    }

    public void setType(InvoiceRecordType type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public InvoiceRecordEntityDTO toDTO() {
        InvoiceRecordEntityDTO dto = new InvoiceRecordEntityDTO();
        dto.setId(id);
        dto.setPriceInCents(priceInCents);
        dto.setSourceRecordCreated(sourceRecordCreated);
        dto.setType(type);
        dto.setDescription(description);
        return dto;
    }
}
