package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.RolesAndRightsDao;
import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableMyUserProfile;
import nl.codebasesoftware.produx.formdata.OtherUserProfileFormData;
import nl.codebasesoftware.produx.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * User: rvanloen
 * Date: 16-7-12
 * Time: 23:01
 */
@Service(value = "userProfileService")
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileDao userProfileDao;
    private ConversionService conversionService;
    private RolesAndRightsDao rolesAndRightsDao;


    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao, ConversionService conversionService, RolesAndRightsDao rolesAndRightsDao) {
        this.userProfileDao = userProfileDao;
        this.conversionService = conversionService;
        this.rolesAndRightsDao = rolesAndRightsDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile findByEmail(String email) {
        return userProfileDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfile> findOthersInCompany(long companyId, UserProfile exludeProfile) {
        return userProfileDao.findOthersInCompany(companyId, exludeProfile);
    }

    @Override
    @Transactional
    public UserProfile findById(long id) {
        return userProfileDao.find(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(BindableMyUserProfile myBindableUserProfile) {
        UserProfile profile = userProfileDao.find(myBindableUserProfile.getId());
        profile.setEmail(myBindableUserProfile.getEmail());
        profile.setLastName(myBindableUserProfile.getLastName());
        profile.setFirstName(myBindableUserProfile.getFirstName());
        profile.setPhone(myBindableUserProfile.getPhone());
        profile.setPreposition(myBindableUserProfile.getPreposition());
        userProfileDao.persist(profile);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(UserProfile profile) {
        UserProfile persistentProfile = userProfileDao.find(profile.getId());
        persistentProfile.setPasswordHash(profile.getPasswordHash());
        persistentProfile.setEmail(profile.getEmail());
        persistentProfile.setFirstName(profile.getFirstName());
        persistentProfile.setLastName(profile.getLastName());
        persistentProfile.setPhone(profile.getPhone());
        userProfileDao.persist(persistentProfile);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(OtherUserProfileFormData profile) {
        UserProfile userProfile = userProfileDao.find(profile.getId());
        List<Role> newRoles = rolesAndRightsDao.findByIds(profile.getRoles());
        userProfile.setRoles(new HashSet<Role>(newRoles));
        userProfile.setEnabled(profile.isEnabled());
        userProfileDao.merge(userProfile);
    }

    @Override
    @Transactional (readOnly = true)
    public UserProfile findAuthorByArticle(long articleId){
        return userProfileDao.findAuthorByArticle(articleId);
    }

    @Override
    @Transactional (readOnly = true)
    public UserProfile findAuthorByPage(long pageId){
        return userProfileDao.findAuthorByPage(pageId);
    }


    public String testing(){
        UserProfile userProfile = userProfileDao.find(1L);
        return userProfile.getFullNameInformal();
    }


}
