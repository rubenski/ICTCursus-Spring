package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import org.springframework.core.convert.converter.Converter;

/**
 * User: rvanloen
 * Date: 21-4-13
 * Time: 3:53
 */
public class AccountRequestToAccountRequestFormData implements Converter<AccountRequest, AccountRequestFormData> {

    @Override
    public AccountRequestFormData convert(AccountRequest req) {
        AccountRequestFormData formData = new AccountRequestFormData();
        formData.setCompanyAddress(req.getCompanyAddress());
        formData.setCompanyCity(req.getCompanyCity());
        formData.setCompanyEmail(req.getCompanyEmail());
        formData.setCompanyName(req.getCompanyName());
        formData.setCompanyPhone(req.getCompanyPhone());
        formData.setCountry(req.getCountry());
        formData.setEmail(req.getEmail());
        formData.setFirstName(req.getFirstName());
        formData.setId(req.getId());
        formData.setLastName(req.getLastName());
        formData.setMessage(req.getUserMessage());
        formData.setPhone(req.getPhone());
        formData.setPreposition(req.getPreposition());
        formData.setTradeNumber(req.getTradeNumber());
        formData.setVatNumber(req.getVatNumber());
        formData.setCompanyZipCode(req.getCompanyZipCode());
        return formData;
    }
}
