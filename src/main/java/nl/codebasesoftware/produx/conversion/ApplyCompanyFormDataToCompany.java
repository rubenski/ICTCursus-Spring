package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.formdata.CompanyFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 4-12-13
 * Time: 19:14
 */
@Component
public class ApplyCompanyFormDataToCompany implements Converter<CompanyFormData, Company> {

    private CompanyDao companyDao;

    @Autowired
    public ApplyCompanyFormDataToCompany(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public Company convert(CompanyFormData companyFormData) {

        Company company = companyDao.find(companyFormData.getId());

        company.setName(companyFormData.getName());
        company.setCompanyPrefix(companyFormData.getCompanyPrefix());
        company.setPhone(companyFormData.getPhone());
        company.setDescription(companyFormData.getDescription());
        company.setEmail(companyFormData.getEmail());
        company.setAddress(companyFormData.getAddress());
        company.setZipCode(companyFormData.getZipCode());
        company.setCity(companyFormData.getCity());
        company.setCountry(companyFormData.getCountry());
        company.setVatNumber(companyFormData.getVatNumber());
        company.setTradeNumber(companyFormData.getTradeNumber());


        return company;
    }
}
