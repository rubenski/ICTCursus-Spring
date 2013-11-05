package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Invoice;

import java.util.List;

/**
 * User: rvanloen
 * Date: 1-11-13
 * Time: 22:45
 */
public interface InvoiceDao extends GenericDao<Invoice> {
    List<Invoice> findForCompany(long companyId);
    Invoice findLastForCompany(long companyId);
}
