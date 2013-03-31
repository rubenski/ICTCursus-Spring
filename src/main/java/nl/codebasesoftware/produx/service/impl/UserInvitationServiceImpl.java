package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.UserInvitationDao;
import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import nl.codebasesoftware.produx.formdata.BindableUserProfile;
import nl.codebasesoftware.produx.net.mail.InvitationMailer;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.UserInvitationService;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 21:46
 */
@Service
public class UserInvitationServiceImpl implements UserInvitationService {

    private UserInvitationDao userInvitationDao;
    private ConversionService conversionService;
    private CompanyService companyService;
    private InvitationMailer invitationMailer;
    private MessageSource messageSource;
    private UserProfileDao userProfileDao;

    @Resource
    Properties properties;

    @Autowired
    public UserInvitationServiceImpl(UserInvitationDao userInvitationDao, ConversionService conversionService,
                                     CompanyService companyService, InvitationMailer invitationMailer,
                                     MessageSource messageSource,
                                     UserProfileDao userProfileDao) {
        this.userInvitationDao = userInvitationDao;
        this.conversionService = conversionService;
        this.companyService = companyService;
        this.invitationMailer = invitationMailer;
        this.messageSource = messageSource;
        this.userProfileDao = userProfileDao;
    }

    @Override
    @Transactional
    public void inviteUserForCurrentCompany(BindableUserInvitation bindableInvitation) throws ProduxServiceException {
        Locale locale = Locale.getDefault();
        setSecurityCode(bindableInvitation);
        setCompanyId(bindableInvitation);
        UserInvitation userInvitation = conversionService.convert(bindableInvitation, UserInvitation.class);
        userInvitationDao.persist(userInvitation);

        try {
            invitationMailer.sendInvitationEmail(userInvitation, locale);
        } catch (MessagingException e) {
            throw new ProduxServiceException(messageSource.getMessage("mail.send.error",new Object[]{}, locale));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserInvitation findBySecurityCode(String code){
        return userInvitationDao.findByCode(code);
    }

    @Transactional(readOnly = true)
    public UserInvitation findByEmail(String email){
        return userInvitationDao.findByEmail(email);
    }

    @Transactional
    public UserProfile activateProfile(BindableUserProfile profile){
        UserProfile userProfile = conversionService.convert(profile, UserProfile.class);
        UserInvitation invitation = userInvitationDao.findByEmail(profile.getEmail());
        userProfile.setRoles(invitation.getRoles());
        userProfileDao.persist(userProfile);

        invitation.setActivated(true);

        userInvitationDao.persist(invitation);

        return userProfile;
    }


    private void setCompanyId(BindableUserInvitation userInvitation) {
        Company currentlyLoggedInCompany = companyService.getCurrentlyLoggedInCompany();
        userInvitation.setCompanyId(currentlyLoggedInCompany.getId());
    }

    private void setSecurityCode(BindableUserInvitation invitation) {
        String property = properties.getProperty("invitation.security.code.length");
        int codeLength = Integer.parseInt(property);
        invitation.setSecurityCode(SecurityUtil.randomAlphaNumericString(codeLength));
    }

}
