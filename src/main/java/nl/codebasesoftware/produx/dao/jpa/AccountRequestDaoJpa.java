package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.AccountRequestDao;
import nl.codebasesoftware.produx.domain.AccountRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:31
 */
@Repository
@SuppressWarnings("unchecked")
public class AccountRequestDaoJpa extends GenericDaoJpa<AccountRequest> implements AccountRequestDao {

    public AccountRequestDaoJpa() {
        super(AccountRequest.class);
    }


    @Override
    public List<AccountRequest> findNonEvaluated() {
        return findRequests(false);
    }

    @Override
    public List<AccountRequest> findEvaluated() {
        return findRequests(true);
    }

    private List<AccountRequest> findRequests(boolean evaluated) {
        return entityManager.createQuery("from AccountRequest ar where ar.evaluated = :evaluated").setParameter("evaluated", evaluated).getResultList();
    }
}
