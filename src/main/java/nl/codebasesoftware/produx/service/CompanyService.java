package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;

/**
 * User: rvanloen
 * Date: 6-11-12
 * Time: 2:35
 */
public interface CompanyService {
    Company findByUserProfile(UserProfile userProfile);
}
