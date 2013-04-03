package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.BindableUserProfile;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * User: rvanloen
 * Date: 31-3-13
 * Time: 3:27
 */
@Component
public class BindableUserProfileToUserProfile implements Converter<BindableUserProfile, UserProfile> {


    private CompanyService companyService;

    @Autowired
    public BindableUserProfileToUserProfile(CompanyService companyService){
        this.companyService = companyService;
    }

    @Override
    public UserProfile convert(BindableUserProfile bindableUserProfile) {

        Company company = companyService.findById(bindableUserProfile.getCompanyId());
        UserProfile profile = new UserProfile();
        profile.setCompany(company);
        profile.setEmail(bindableUserProfile.getEmail());
        profile.setLastName(bindableUserProfile.getLastName());
        profile.setFirstName(bindableUserProfile.getFirstName());
        profile.setPasswordHash(SecurityUtil.createPasswordHash(bindableUserProfile.getPassword1()));
        profile.setEnabled(true);
        profile.setPhone(bindableUserProfile.getPhone());
        profile.setPreposition(bindableUserProfile.getPreposition());

        return profile;

    }

}
