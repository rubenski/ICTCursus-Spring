package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.UserInvitationDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.formdata.BindableUserInvitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 21-3-13
 * Time: 22:03
 */
@Component
public class BindableUserInvitationToUserInvitation implements Converter<BindableUserInvitation, UserInvitation> {

    private UserInvitationDao userInvitationDao;
    private CompanyDao companyDao;

    @Autowired
    public BindableUserInvitationToUserInvitation(UserInvitationDao userInvitationDao, CompanyDao companyDao) {
        this.userInvitationDao = userInvitationDao;
        this.companyDao = companyDao;
    }

    @Override
    public UserInvitation convert(BindableUserInvitation bindableInvitation) {

        UserInvitation invitation = userInvitationDao.findByEmail(bindableInvitation.getEmail());
        if (invitation == null) {
            invitation = new UserInvitation();
        }

        Company company = companyDao.find(bindableInvitation.getCompanyId());

        invitation.setCompany(company);
        invitation.setSecurityCode(bindableInvitation.getSecurityCode());
        invitation.setFirstName(bindableInvitation.getFirstName());
        invitation.setLastName(bindableInvitation.getFirstName());
        invitation.setEmail(bindableInvitation.getEmail());

        return invitation;
    }
}
