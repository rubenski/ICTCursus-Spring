package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.CompanyFormData;
import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.formdata.CompanyProductSettingsFormData;
import nl.codebasesoftware.produx.formdata.CompanySettingsFormData;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 6-11-12
 * Time: 2:35
 */
public interface CompanyService {
    CompanyEntityDTO findByUserProfile(UserProfileEntityDTO userProfile);

    void update(CompanyFormData companyFormData);

    void update(CompanyProductSettingsFormData formData);

    CompanyEntityDTO getCurrentlyLoggedInCompany();

    CompanyEntityDTO findById(Long companyId);

    CompanyEntityDTO findByPrefix(String prefix);

    CompanyEntityDTO findByArticle(ArticleEntityDTO article);

    CompanySettingsFormData getCompanySettingsForCurrentCompany();

    void saveSettings(CompanySettingsFormData settingsDto);

    void updateLogo(BindableFileUpload bindableFileUpload);

    void removeLogo(long companyId);

    List<CompanyEntityDTO> findAll();

    void updateWarningMailSent(CompanyEntityDTO companyEntityDTO, Calendar instance);
}
