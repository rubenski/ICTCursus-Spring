package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.UserInvitationDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.UserInvitationService;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private Properties properties;

    @Autowired
    public UserInvitationServiceImpl(UserInvitationDao userInvitationDao, ConversionService conversionService,
                                     CompanyService companyService, Properties properties) {
        this.userInvitationDao = userInvitationDao;
        this.conversionService = conversionService;
        this.companyService = companyService;
        this.properties = properties;
    }

    @Override
    @Transactional
    public void inviteUserForCurrentCompany(BindableUserInvitation bindableInvitation) {
        setSecurityCode(bindableInvitation);
        setCompanyId(bindableInvitation);
        UserInvitation userInvitation = conversionService.convert(bindableInvitation, UserInvitation.class);
        userInvitationDao.persist(userInvitation);
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
