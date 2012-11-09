package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: rvanloen
 * Date: 6-11-12
 * Time: 2:36
 */
@Service
public class CompanyServiceImpl implements CompanyService{


    @Override
    @Transactional(readOnly = true)
    public Company findByUserProfile(UserProfile userProfile) {
        return userProfile.getCompany();
    }
}



