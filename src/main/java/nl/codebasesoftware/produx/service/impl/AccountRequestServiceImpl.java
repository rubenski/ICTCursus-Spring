package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.AccountRequestDao;
import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.formdata.AccountRequestDecision;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import nl.codebasesoftware.produx.service.AccountRequestService;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:30
 */
@Service
public class AccountRequestServiceImpl implements AccountRequestService {

    private AccountRequestDao accountRequestDao;
    private ConversionService conversionService;
    private Properties properties;

    @Autowired
    public AccountRequestServiceImpl(AccountRequestDao accountRequestDao, ConversionService conversionService, Properties properties) {
        this.accountRequestDao = accountRequestDao;
        this.conversionService = conversionService;
        this.properties = properties;
    }


    @Override
    @Transactional(readOnly = false)
    public void save(AccountRequestFormData formData) {
        AccountRequest request = conversionService.convert(formData, AccountRequest.class);
        setSecurityCode(request);
        request.setRequestDate(Calendar.getInstance());
        accountRequestDao.persist(request);
    }

    private void setSecurityCode(AccountRequest request) {
        String property = properties.getProperty("security.code.length");
        int codeLength = Integer.parseInt(property);
        request.setSecurityCode(SecurityUtil.randomAlphaNumericString(codeLength));
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    public List<AccountRequest> findNonEvaluated() {
        return accountRequestDao.findNonEvaluated();
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    public AccountRequest find(long id){
        return accountRequestDao.find(id);
    }

    @Override
    @Transactional(readOnly = false)
    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    public void decide(AccountRequestDecision accountRequestDecision) {
        AccountRequest request = accountRequestDao.find(accountRequestDecision.getArticleRequestId());
        request.setEvaluated(true);
        Boolean decision = accountRequestDecision.getDecision() == 1;
        request.setGranted(decision);
        accountRequestDao.persist(request);

        if(decision){
            Company company = new Company();

        }
    }
}
