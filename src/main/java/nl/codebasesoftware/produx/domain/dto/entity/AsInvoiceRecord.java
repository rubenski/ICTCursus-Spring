package nl.codebasesoftware.produx.domain.dto.entity;

import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 4-11-13
 * Time: 20:54
 */
public interface AsInvoiceRecord {
    long getInvoicePriceInCents();
    String getInvoiceDescription();
    Calendar getCreated();
}
