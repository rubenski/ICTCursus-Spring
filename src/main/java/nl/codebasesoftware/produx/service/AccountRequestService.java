package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.formdata.AccountRequestDecision;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;

import java.util.List;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:30
 */
public interface AccountRequestService {
    void save(AccountRequestFormData formData);
    List<AccountRequest> findNonEvaluated();
    AccountRequest find(long id);
    void decide(AccountRequestDecision decision);
}
