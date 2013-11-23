package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.BindableCompany;
import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.formdata.CompanySettingsFormData;

/**
 * User: rvanloen
 * Date: 6-11-12
 * Time: 2:35
 */
public interface CompanyService {
    CompanyEntityDTO findByUserProfile(UserProfileEntityDTO userProfile);

    void update(BindableCompany bindableCompany);

    Company getCurrentlyLoggedInCompany();

    CompanyEntityDTO findById(Long companyId);

    CompanyEntityDTO findByPrefix(String prefix);

    Company findByArticle(ArticleEntityDTO article);

    CompanySettingsFormData getCompanySettingsForCurrentCompany();

    void saveSettings(CompanySettingsFormData settingsDto);

    void updateLogo(BindableFileUpload bindableFileUpload);

    void removeLogo(long companyId);
}
