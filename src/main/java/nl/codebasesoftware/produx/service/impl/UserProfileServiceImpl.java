package nl.codebasesoftware.produx.service.impl;

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
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileDao userProfileDao;

    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile authenticate(String username, String password) {
        return null;
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
    @Transactional
    public void update(UserProfile userProfile) {
        userProfileDao.updateDetached(userProfile, new String[]{"password"});
    }
}
