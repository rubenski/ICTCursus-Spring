package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.RolesAndRightsDao;
import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.ProfileStatus;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import nl.codebasesoftware.produx.formdata.BindableMyUserProfile;
import nl.codebasesoftware.produx.formdata.BindableSysAdminUserProfile;
import nl.codebasesoftware.produx.formdata.OtherUserProfileFormData;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.util.collection.EntityCollectionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

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
    private MessageSource messageSource;


    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao, ConversionService conversionService, RolesAndRightsDao rolesAndRightsDao, MessageSource messageSource) {
        this.userProfileDao = userProfileDao;
        this.conversionService = conversionService;
        this.rolesAndRightsDao = rolesAndRightsDao;
        this.messageSource = messageSource;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileEntityDTO findByEmail(String email) {
        UserProfile user = userProfileDao.findByEmail(email);
        if(user == null){
            return null;
        }
        return user.toDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfile> findOthersInCompany(long companyId, long excludedProfileId) {
        return userProfileDao.findOthersInCompany(companyId, excludedProfileId);
    }

    @Override
    @Transactional
    public UserProfileEntityDTO findById(long id) {
        UserProfile profile = userProfileDao.find(id);
        if(profile != null){
            return profile.toDTO();
        }
        return null;
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
    public void update(BindableSysAdminUserProfile bindableSysAdminUserProfile) {
        UserProfile userProfile = userProfileDao.find(bindableSysAdminUserProfile.getId());
        userProfile.setEmail(bindableSysAdminUserProfile.getEmail());
        userProfile.setEnabled(bindableSysAdminUserProfile.isEnabled());
        // userProfileDao.persist(userProfile);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(UserProfileEntityDTO profile) {
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
    @Transactional(readOnly = true)
    public UserProfile findAuthorByArticle(long articleId) {
        return userProfileDao.findAuthorByArticle(articleId);
    }

    @Override
    public List<UserProfileEntityDTO> findByRole(RoleName role) {
        return new EntityCollectionConverter<UserProfile, UserProfileEntityDTO>().convert(userProfileDao.findByRole(role));
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile findAuthorByPage(long pageId) {
        return userProfileDao.findAuthorByPage(pageId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileEntityDTO findWithCompany(Long id) {
        UserProfile user = userProfileDao.findWithCompany(id);
        if(user == null){
            return null;
        }
        return user.toDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileStatus> getProfileStatuses(Locale locale) {
        String active = messageSource.getMessage("generic.message.active", new Object[]{}, locale);
        String inactive = messageSource.getMessage("generic.message.inactive", new Object[]{}, locale);
        List<ProfileStatus> statuses = new ArrayList<ProfileStatus>();
        statuses.add(new ProfileStatus(false, inactive));
        statuses.add(new ProfileStatus(true, active));
        return statuses;
    }

    @Override
    @Transactional(readOnly = true)
    public void removeProfile(Long id) {
        UserProfile userProfile = userProfileDao.find(id);
        userProfileDao.delete(userProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileEntityDTO getCurrentlyLoggedInUser() {
        return userProfileDao.find(CurrentUser.get().getId()).toDTO();
    }
}
