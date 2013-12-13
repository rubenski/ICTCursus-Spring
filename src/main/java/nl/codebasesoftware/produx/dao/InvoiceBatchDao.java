package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.InvoiceBatch;

import java.util.List;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 22:02
 */
public interface InvoiceBatchDao {
    void save(InvoiceBatch invoiceBatch);
    List<InvoiceBatch> findByMonthAndYear(int month, int year);
}
