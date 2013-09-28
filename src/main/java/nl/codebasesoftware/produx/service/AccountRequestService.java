package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import nl.codebasesoftware.produx.service.business.BasicAccountRequestEvaluation;

import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:30
 */
public interface AccountRequestService {
    void save(AccountRequestFormData formData);

    List<BasicAccountRequestEvaluation> findNonEvaluated();

    AccountRequest find(long id);

    void grant(long accountRequestId, String message, Locale locale);

    void reject(long requestId, String message, Locale locale);

    List<BasicAccountRequestEvaluation> findEvaluated();
}
