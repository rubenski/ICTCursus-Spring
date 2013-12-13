package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.InvoiceBatchDao;
import nl.codebasesoftware.produx.domain.InvoiceBatch;
import nl.codebasesoftware.produx.domain.InvoiceProcessingAttempt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 22:03
 */
@Repository
public class InvoiceBatchDaoJpa extends GenericDaoJpa<InvoiceBatch> implements InvoiceBatchDao {

    public InvoiceBatchDaoJpa() {
        super(InvoiceBatch.class);
    }

    @Override
    public void save(InvoiceBatch invoiceBatch) {
        entityManager.persist(invoiceBatch);

        for (InvoiceProcessingAttempt invoiceProcessingAttempt : invoiceBatch.getInvoiceProcessingAttempts()) {
            invoiceProcessingAttempt.setBatch(invoiceBatch);
            entityManager.persist(invoiceProcessingAttempt);
        }
    }

    @Override
    public List<InvoiceBatch> findByMonthAndYear(int month, int year) {
        return entityManager.createQuery("from InvoiceBatch where month = :month and year = :year")
                .setParameter("month", month)
                .setParameter("year", year)
                .getResultList();
    }
}
