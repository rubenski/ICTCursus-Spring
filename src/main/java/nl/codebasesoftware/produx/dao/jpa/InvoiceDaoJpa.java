package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.InvoiceDao;
import nl.codebasesoftware.produx.domain.Invoice;
import nl.codebasesoftware.produx.domain.InvoiceRecord;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: rvanloen
 * Date: 1-11-13
 * Time: 22:45
 */
@Repository
@SuppressWarnings("unchecked")
public class InvoiceDaoJpa extends GenericDaoJpa<Invoice> implements InvoiceDao {


    public InvoiceDaoJpa() {
        super(Invoice.class);
    }

    @Override
    public List<Invoice> findForCompany(long companyId) {
        return entityManager.createQuery("from Invoice i where i.company.id = :id order by i.dateCreated").setParameter("id", companyId).getResultList();
    }

    @Override
    public Invoice findLastForCompany(long companyId) {
        List<Invoice> list = entityManager.createQuery("from Invoice i where i.company.id = :id order by i.dateCreated").setParameter("id", companyId).getResultList();
        if(list.size() > 0){
            return list.get(list.size() - 1);
        }
        return null;
    }

    @Override
    public List<Invoice> find(long companyId, int year) {
        return entityManager.createQuery("from Invoice i " +
                "where i.company.id = :id " +
                "and i.forYear = :year " +
                "order by i.dateCreated")
                .setParameter("id", companyId)
                .setParameter("year", year)
                .getResultList();

    }

    @Override
    public void save(Invoice invoice) {

        entityManager.persist(invoice);
        for (InvoiceRecord invoiceRecord : invoice.getRecords()) {
            invoiceRecord.setInvoice(invoice);
            entityManager.persist(invoiceRecord);
        }
    }
}
