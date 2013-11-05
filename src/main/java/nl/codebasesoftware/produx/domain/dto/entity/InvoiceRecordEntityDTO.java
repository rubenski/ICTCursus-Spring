package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.InvoiceRecordType;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceRecordEntityDTO extends DomainEntityDTO {

    private Long id;
    private long priceInCents;
    private Calendar sourceRecordCreated;
    private String description;
    private InvoiceRecordType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(long price) {
        this.priceInCents = price;
    }

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

    public InvoiceRecordType getType() {
        return type;
    }

    public void setType(InvoiceRecordType type) {
        this.type = type;
    }

    public String getCreationDatePretty(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String format = df.format(sourceRecordCreated.getTime());
        return format;
    }

    public String getEuroPrice(){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        return format.format(priceInCents/100);
    }
}
