package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.AccountRequest;

import java.util.List;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:31
 */
public interface AccountRequestDao extends GenericDao<AccountRequest>{
    List<AccountRequest> findNonEvaluated();
}
