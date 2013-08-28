package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceBatchEntityDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 20-5-13
 * Time: 23:49
 */
public class InvoiceBatch implements DomainEntity {

    private Long id;
    private Calendar date;
    private Calendar completed;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public InvoiceBatchEntityDTO toDTO() {

        InvoiceBatchEntityDTO dto = new InvoiceBatchEntityDTO();
        dto.setId(id);
        dto.setCompleted(completed);
        dto.setDate(date);
        return dto;
    }
}
