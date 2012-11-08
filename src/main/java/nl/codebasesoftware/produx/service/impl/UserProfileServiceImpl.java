package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.NumberUtil;

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
    public UserProfile findById(Long id) {
        return userProfileDao.find(id);
    }

    @Override
    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void removeUserProfile(String username) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile findByEmail(String email) {
        return userProfileDao.findByEmail(email);
    }

    @Override
    public String generateRandomPassword() {
        String randomString = "";
        for(int i = 0; i < 10; i++){
            int randomInt = NumberUtil.randomInt(97, 122);
            randomString += (char) randomInt;
        }

        return randomString;
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
        // persistentProfile.setRoles(profile.getRoles());
        userProfileDao.persist(persistentProfile);
    }
}
