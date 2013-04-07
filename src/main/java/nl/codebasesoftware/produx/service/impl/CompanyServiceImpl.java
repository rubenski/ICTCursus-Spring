package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Logo;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableCompany;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * User: rvanloen
 * Date: 6-11-12
 * Time: 2:36
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDao;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Company findByUserProfile(UserProfile userProfile) {
        return userProfile.getCompany();
    }

    @Override
    @Transactional
    public void update(BindableCompany bindableCompany) {
        Company company = getCurrentlyLoggedInCompany();
        bindableCompanyToCompany(bindableCompany, company);
        companyDao.persist(company);
    }

    @Override
    @Transactional
    public Company getCurrentlyLoggedInCompany() {
        UserProfile user = CurrentUser.get();
        Company company = user.getCompany();
        return companyDao.find(company.getId());
    }

    private void bindableCompanyToCompany(BindableCompany bindableCompany, Company company){
        try {
            BeanUtils.copyProperties(company, bindableCompany);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional (readOnly = true)
    public void setLogo(Logo logo, Long companyId) {
        companyDao.setLogo(logo, companyId);
    }

    @Override
    @Transactional (readOnly = true)
    public Company findById(Long companyId) {
        return companyDao.find(companyId);
    }

    @Override
    @Transactional (readOnly = false)
    public void updateLogo(byte[] bytes) {
        Company company = getCurrentlyLoggedInCompany();
        company.setLogo(bytes);
        companyDao.persist(company);
    }

    @Override
    @Transactional (readOnly = true)
    public byte[] getLogo(Long companyId) {
        Company company = companyDao.find(companyId);
        return company.getLogo();
    }
}



