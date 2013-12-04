package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.formdata.CompanyProductSettingsFormData;
import nl.codebasesoftware.produx.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 4-12-13
 * Time: 20:24
 */
@Component
public class ApplyCompanyProductSettingsFormDataToCompany implements Converter<CompanyProductSettingsFormData, Company> {

    private CompanyDao companyDao;

    @Autowired
    public ApplyCompanyProductSettingsFormDataToCompany(CompanyDao companyDao){
        this.companyDao = companyDao;
    }

    @Override
    public Company convert(CompanyProductSettingsFormData data) {

        Company company = companyDao.find(data.getCompanyId());

        company.getProductSettings().setCompanyInfoActive(data.isCompanyInfoActive());
        company.getProductSettings().setCourseListingType(data.getCourseListingType());
        company.getProductSettings().setExternalCourseLinksActive(data.isExternalCourseLinksActive());

        return company;

    }
}
