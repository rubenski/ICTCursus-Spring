package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.dao.AccountRequestDao;
import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:35
 */
@Component
public class AccountRequestFormDataToAccountRequest implements Converter<AccountRequestFormData, AccountRequest> {

    private AccountRequestDao accountRequestDao;

    @Autowired
    public AccountRequestFormDataToAccountRequest(AccountRequestDao accountRequestDao) {
        this.accountRequestDao = accountRequestDao;
    }

    @Override
    public AccountRequest convert(AccountRequestFormData accountRequestFormData) {

        AccountRequest request;

        if (accountRequestFormData.isPersisted()) {
            request = accountRequestDao.find(accountRequestFormData.getId());
        } else {
            request = new AccountRequest();
            request.setRequestDate(Calendar.getInstance());
        }

        request.setEmail(accountRequestFormData.getEmail());
        request.setFirstName(accountRequestFormData.getFirstName());
        request.setId(accountRequestFormData.getId());
        request.setLastName(accountRequestFormData.getLastName());
        request.setUserMessage(accountRequestFormData.getMessage());
        request.setPhone(accountRequestFormData.getPhone());
        request.setPreposition(accountRequestFormData.getPreposition());
        request.setTradeNumber(accountRequestFormData.getTradeNumber());
        request.setCompanyAddress(accountRequestFormData.getCompanyAddress());
        request.setCompanyCity(accountRequestFormData.getCompanyCity());
        request.setCompanyEmail(accountRequestFormData.getCompanyEmail());
        request.setCompanyPhone(accountRequestFormData.getCompanyPhone());
        request.setCompanyName(accountRequestFormData.getCompanyName());
        request.setCountry(accountRequestFormData.getCountry());
        request.setVatNumber(accountRequestFormData.getVatNumber());
        request.setCompanyZipCode(accountRequestFormData.getCompanyZipCode());

        if(accountRequestFormData.getCompanyPrefix() != null){
            request.setCompanyPrefix(accountRequestFormData.getCompanyPrefix().toUpperCase());
        }


        return request;
    }
}
