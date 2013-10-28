package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;
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
    private int price;
    private Calendar sourceRecordCreated;
    private Map<String, String> sourceFields = new HashMap<>();
    private InvoiceRecordType type;

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
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Calendar getSourceRecordCreated() {
        return sourceRecordCreated;
    }

    public void setSourceRecordCreated(Calendar sourceRecordCreated) {
        this.sourceRecordCreated = sourceRecordCreated;
    }

    @Column(name = "record_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public InvoiceRecordType getType() {
        return type;
    }

    public void setType(InvoiceRecordType type) {
        this.type = type;
    }

    @Override
    public InvoiceRecordEntityDTO toDTO() {
        InvoiceRecordEntityDTO dto = new InvoiceRecordEntityDTO();
        dto.setId(id);
        dto.setPrice(price);
        dto.setSourceRecordCreated(sourceRecordCreated);
        return dto;
    }
}
