package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.formdata.AccountActivationFormData;
import org.springframework.core.convert.converter.Converter;


/**
 * User: rvanloen
 * Date: 6-4-13
 * Time: 15:44
 */
public class UserInvitationToAccountActivationFormData implements Converter<UserInvitation, AccountActivationFormData> {

    @Override
    public AccountActivationFormData convert(UserInvitation invitation) {
        AccountActivationFormData formData = new AccountActivationFormData();
        formData.setCompanyId(invitation.getCompany().getId());
        formData.setEmail(invitation.getEmail());
        formData.setFirstName(invitation.getFirstName());
        formData.setInvitationId(invitation.getId());
        formData.setLastName(invitation.getLastName());
        formData.setPreposition(invitation.getPreposition());
        return formData;
    }
}
