package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.InvoiceDao;
import nl.codebasesoftware.produx.domain.Invoice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: rvanloen
 * Date: 1-11-13
 * Time: 22:45
 */
@Repository
public class InvoiceDaoJpa extends GenericDaoJpa<Invoice> implements InvoiceDao {


    public InvoiceDaoJpa() {
        super(Invoice.class);
    }

    @Override
    public List<Invoice> findForCompany(long companyId) {
        return entityManager.createQuery("from Invoice i where i.company.id = :id").setParameter("id", companyId).getResultList();
    }
}
