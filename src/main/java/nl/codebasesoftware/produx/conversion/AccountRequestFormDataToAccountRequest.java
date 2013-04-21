package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:35
 */
public class AccountRequestFormDataToAccountRequest implements Converter<AccountRequestFormData, AccountRequest> {
    @Override
    public AccountRequest convert(AccountRequestFormData accountRequestFormData) {
        AccountRequest request = new AccountRequest();
        request.setCompanyName(accountRequestFormData.getCompanyName());
        request.setEmail(accountRequestFormData.getEmail());
        request.setFirstName(accountRequestFormData.getFirstName());
        request.setId(accountRequestFormData.getId());
        request.setLastName(accountRequestFormData.getLastName());
        request.setMessage(accountRequestFormData.getMessage());
        request.setPhone(accountRequestFormData.getPhone());
        request.setPreposition(accountRequestFormData.getPreposition());
        request.setTradeNumber(accountRequestFormData.getTradeNumber());
        return request;
    }
}
