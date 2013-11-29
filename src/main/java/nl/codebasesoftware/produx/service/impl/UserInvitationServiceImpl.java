package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.RolesAndRightsDao;
import nl.codebasesoftware.produx.dao.UserInvitationDao;
import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.formdata.AccountActivationFormData;
import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import nl.codebasesoftware.produx.net.mail.InvitationMailer;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.UserInvitationService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.properties.Properties;
import nl.codebasesoftware.produx.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.*;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 21:46
 */
@Service
public class UserInvitationServiceImpl implements UserInvitationService {

    private UserInvitationDao userInvitationDao;
    private ConversionService conversionService;
    private InvitationMailer invitationMailer;
    private MessageSource messageSource;
    private UserProfileDao userProfileDao;
    private RolesAndRightsDao rolesAndRightsDao;
    private CompanyDao companyDao;
    private CompanyService companyService;

    @Resource
    Properties properties;

    @Autowired
    public UserInvitationServiceImpl(UserInvitationDao userInvitationDao, ConversionService conversionService,
                                     InvitationMailer invitationMailer,
                                     MessageSource messageSource,
                                     UserProfileDao userProfileDao,
                                     RolesAndRightsDao rolesAndRightsDao,
                                     CompanyDao companyDao,
                                     CompanyService companyService) {
        this.userInvitationDao = userInvitationDao;
        this.conversionService = conversionService;
        this.invitationMailer = invitationMailer;
        this.messageSource = messageSource;
        this.userProfileDao = userProfileDao;
        this.rolesAndRightsDao = rolesAndRightsDao;
        this.companyDao = companyDao;
        this.companyService = companyService;
    }

    @Override
    @Transactional(readOnly = false)
    public void inviteUserForCurrentCompany(BindableUserInvitation bindableInvitation) throws ProduxServiceException {
        Locale locale = LocaleContextHolder.getLocale();
        setSecurityCode(bindableInvitation);
        setCompanyId(bindableInvitation);
        UserInvitation userInvitation = conversionService.convert(bindableInvitation, UserInvitation.class);
        userInvitation.setCreationDate(Calendar.getInstance().getTime());
        userInvitationDao.persist(userInvitation);

        try {
            invitationMailer.sendInvitationEmail(userInvitation, locale);
        } catch (MessagingException e) {
            throw new ProduxServiceException(messageSource.getMessage("mail.send.error", new Object[]{}, locale));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserInvitation findBySecurityCode(String code) {
        return userInvitationDao.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInvitation> findByInviter(long inviterProfileId) {
        return userInvitationDao.findByInviter(inviterProfileId);
    }

    @Transactional(readOnly = true)
    public UserInvitation findByEmail(String email) {
        return userInvitationDao.findByEmail(email);
    }

    @Transactional(readOnly = false)
    public void removeInvitation(long invitationId) {
        UserInvitation invitation = userInvitationDao.find(invitationId);
        UserProfileEntityDTO currentUser = CurrentUser.get();

        // Someone is trying to remove an invitation that is not his/hers or that has already been activated
        if (invitation == null || !currentUser.getId().equals(invitation.getInvitedBy().getId())) {
            return;
        }

        // There is no orphan removal for many to many relationships, so let's remove the orphans ourselves
        invitation.setRoles(new HashSet<Role>());
        userInvitationDao.persist(invitation);
        userInvitationDao.delete(invitation);

    }

    @Transactional(readOnly = false)
    public UserProfileEntityDTO activateProfile(AccountActivationFormData activationData) {
        UserProfile userProfile = new UserProfile();
        Company company = companyDao.find(activationData.getCompanyId());
        userProfile.setCompany(company);
        userProfile.setEmail(activationData.getEmail());
        userProfile.setEnabled(true);
        userProfile.setFirstName(activationData.getFirstName());
        userProfile.setPreposition(activationData.getPreposition());
        userProfile.setLastName(activationData.getLastName());
        userProfile.setPhone(activationData.getPhone());
        userProfile.setPasswordHash(SecurityUtil.createShaHash(activationData.getPassword1()));

        // Set invitation to activated
        UserInvitation invitation = userInvitationDao.findByEmail(activationData.getEmail());
        invitation.setActivated(true);

        // Transfer roles from invitation to user profile
        transferRoles(invitation, userProfile);
        userProfileDao.persist(userProfile);

        userInvitationDao.persist(invitation);

        return userProfile.toDTO();
    }


    // This method transfers assigned roles from the invitation to the userprofile.
    // Somehow doing it directly by calling the setRoles method of the profile
    // with the getRoles method of the invitation as its argument
    // creates a "found shared references to a collection" exception. Not sure why.
    private void transferRoles(UserInvitation invitation, UserProfile profile) {
        Set<Role> roles = new HashSet<Role>();
        for (Role role : invitation.getRoles()) {
            Role persistedRole = rolesAndRightsDao.find(role.getId());
            roles.add(persistedRole);
        }
        profile.setRoles(roles);
    }


    private void setCompanyId(BindableUserInvitation userInvitation) {
        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        userInvitation.setCompanyId(currentlyLoggedInCompany.getId());
    }

    private void setSecurityCode(BindableUserInvitation invitation) {
        String property = properties.getProperty("security.code.length");
        int codeLength = Integer.parseInt(property);
        invitation.setSecurityCode(SecurityUtil.randomAlphaNumericString(codeLength));
    }

}
