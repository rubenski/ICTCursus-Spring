package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableCompany;

/**
 * User: rvanloen
 * Date: 6-11-12
 * Time: 2:35
 */
public interface CompanyService {
    Company findByUserProfile(UserProfile userProfile);
    void update(BindableCompany bindableCompany);
    Company getCurrentlyLoggedInCompany();
    Company findById(Long companyId);
    void updateLogo(byte[] bytes);
    byte[] getLogo(Long companyId);
    Company findByArticle(Article article);
}
