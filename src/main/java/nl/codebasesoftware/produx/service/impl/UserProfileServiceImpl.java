package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: rvanloen
 * Date: 16-7-12
 * Time: 23:01
 */
@Service(value = "userProfileService")
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileDao userProfileDao;
    private CompanyDao companyDao;

    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao, CompanyDao companyDao) {
        this.userProfileDao = userProfileDao;
        this.companyDao = companyDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile findByEmail(String email) {
        return userProfileDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfile> findByCompany(long companyId) {
        return userProfileDao.findByCompany(companyId);
    }

    @Override
    @Transactional
    public UserProfile findById(long id){
        return userProfileDao.find(id);
    }



    @Override
    @Transactional
    public void update(UserProfile profile) {
        UserProfile persistentProfile = userProfileDao.find(profile.getId());
        persistentProfile.setPasswordHash(profile.getPasswordHash());
        persistentProfile.setEmail(profile.getEmail());
        persistentProfile.setFirstName(profile.getFirstName());
        persistentProfile.setLastName(profile.getLastName());
        persistentProfile.setPhone(profile.getPhone());
        userProfileDao.persist(persistentProfile);
    }
}
