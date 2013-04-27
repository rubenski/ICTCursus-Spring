package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.DwrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: rvanloen
 * Date: 26-4-13
 * Time: 2:42
 */
public class DwrServiceImpl implements DwrService {

    private UserProfileDao userProfileDao;

    @Autowired
    public DwrServiceImpl(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    @Override
    @Transactional(readOnly = true)
    public String findCourseLockingUser(long courseId) {
        UserProfile lockingUser = userProfileDao.findCourseLockingUser(courseId);
        return "some guy";
    }
}
