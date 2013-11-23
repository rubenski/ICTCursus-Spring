package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.RolesAndRightsDao;
import nl.codebasesoftware.produx.dao.UserInvitationDao;
import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import nl.codebasesoftware.produx.service.RolesAndRightService;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 22:03
 */
@Component
public class BindableUserInvitationToUserInvitation implements Converter<BindableUserInvitation, UserInvitation> {

    private UserInvitationDao userInvitationDao;
    private CompanyDao companyDao;
    private RolesAndRightsDao rolesAndRightDao;
    private UserProfileDao userProfileDao;


    @Autowired
    public BindableUserInvitationToUserInvitation(UserInvitationDao userInvitationDao, CompanyDao companyDao,
                                                  RolesAndRightsDao rolesAndRightDao, UserProfileDao userProfileDao) {
        this.userInvitationDao = userInvitationDao;
        this.companyDao = companyDao;
        this.rolesAndRightDao = rolesAndRightDao;
        this.userProfileDao = userProfileDao;
    }

    @Override
    public UserInvitation convert(BindableUserInvitation bindableInvitation) {


        UserInvitation invitation = new UserInvitation();


        Company company = companyDao.find(bindableInvitation.getCompanyId());
        List<Role> roles = rolesAndRightDao.findByIds(bindableInvitation.getRoles());

        invitation.setRoles(new HashSet<Role>(roles));
        invitation.setCompany(company);
        invitation.setSecurityCode(bindableInvitation.getSecurityCode());
        invitation.setFirstName(bindableInvitation.getFirstName());
        invitation.setLastName(bindableInvitation.getLastName());
        invitation.setEmail(bindableInvitation.getEmail());
        invitation.setInvitedBy(userProfileDao.find(CurrentUser.get().getId()));
        invitation.setPreposition(bindableInvitation.getPreposition());
        invitation.setActivated(bindableInvitation.isActivated());

        return invitation;
    }
}
