package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.AccountRequestDao;
import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.RolesAndRightsDao;
import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Role;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.formdata.AccountRequestFormData;
import nl.codebasesoftware.produx.net.mail.AccountRequestResultMailer;
import nl.codebasesoftware.produx.service.AccountRequestService;
import nl.codebasesoftware.produx.service.SecurityService;
import nl.codebasesoftware.produx.service.business.BasicAccountRequestEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:30
 */
@Service
public class AccountRequestServiceImpl implements AccountRequestService {

    private AccountRequestDao accountRequestDao;
    private ConversionService conversionService;
    private CompanyDao companyDao;
    private UserProfileDao userProfileDao;
    private SecurityService securityService;
    private RolesAndRightsDao rolesAndRightsDao;
    private AccountRequestResultMailer accountRequestResultMailer;

    @Autowired
    public AccountRequestServiceImpl(AccountRequestDao accountRequestDao, ConversionService conversionService,
                                     CompanyDao companyDao, UserProfileDao userProfileDao, SecurityService securityService,
                                     RolesAndRightsDao rolesAndRightsDao, AccountRequestResultMailer accountRequestResultMailer) {
        this.accountRequestDao = accountRequestDao;
        this.conversionService = conversionService;
        this.companyDao = companyDao;
        this.userProfileDao = userProfileDao;
        this.securityService = securityService;
        this.rolesAndRightsDao = rolesAndRightsDao;
        this.accountRequestResultMailer = accountRequestResultMailer;
    }


    @Override
    @Transactional(readOnly = false)
    public void save(AccountRequestFormData formData) {
        AccountRequest request = conversionService.convert(formData, AccountRequest.class);
        accountRequestDao.persist(request);
    }


    @Override
    @Transactional(readOnly = true)
    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    public List<BasicAccountRequestEvaluation> findNonEvaluated() {
        List<AccountRequest> requests = accountRequestDao.findNonEvaluated();
        return convertToBasicAccountRequestEvaluations(requests);
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    public List<BasicAccountRequestEvaluation> findEvaluated() {
        List<AccountRequest> requests = accountRequestDao.findEvaluated();
        return convertToBasicAccountRequestEvaluations(requests);
    }

    private List<BasicAccountRequestEvaluation> convertToBasicAccountRequestEvaluations(List<AccountRequest> requests) {
        List<Company> existingCompanies = companyDao.findAll();
        List<UserProfile> existingUserProfiles = userProfileDao.findAll();
        List<BasicAccountRequestEvaluation> basicAccountRequestEvaluations = new ArrayList<BasicAccountRequestEvaluation>();

        for (AccountRequest request : requests) {
            BasicAccountRequestEvaluation unreviewedAccountRequest = new BasicAccountRequestEvaluation(request, existingCompanies, existingUserProfiles);
            basicAccountRequestEvaluations.add(unreviewedAccountRequest);
        }

        return basicAccountRequestEvaluations;
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    public AccountRequest find(long id) {
        return accountRequestDao.find(id);
    }

    @Override
    @Transactional(readOnly = false)
    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    public void reject(long requestId, String message, Locale locale) {
        AccountRequest accountRequest = accountRequestDao.find(requestId);
        accountRequest.reject(message);
        accountRequestResultMailer.sendRejectionMail(accountRequest, locale);
    }

    @Override
    @Transactional(readOnly = false)
    @Secured("hasAnyRole('ROLE_PERM_access_sysadmin_screens')")
    public void grant(long accountRequestId, String message, Locale locale) {
        AccountRequest accountRequest = accountRequestDao.find(accountRequestId);
        accountRequest.grant();
        String randomPassword = securityService.getRandomPassword();
        Company company = createCompanyFromAccountRequest(accountRequest);
        createUserProfileFromAccountRequest(accountRequest, company, randomPassword);
        accountRequestResultMailer.sendAcceptedMail(accountRequest, randomPassword, locale);
    }


    private Company createCompanyFromAccountRequest(AccountRequest request) {
        Company company = new Company();
        company.setAddress(request.getCompanyAddress());
        company.setTradeNumber(request.getTradeNumber());
        company.setCity(request.getCompanyCity());
        company.setCountry(request.getCountry());
        company.setEmail(request.getCompanyEmail());
        company.setName(request.getCompanyName());
        company.setPhone(request.getCompanyPhone());
        company.setVatNumber(request.getVatNumber());
        company.setZipCode(request.getCompanyZipCode());
        companyDao.persist(company);
        return company;
    }

    private void createUserProfileFromAccountRequest(AccountRequest request, Company company, String password) {
        UserProfile profile = new UserProfile();
        profile.setCompany(company);
        profile.setEmail(request.getEmail());
        profile.setEnabled(true);
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhone(request.getPhone());

        // Set password
        profile.setPasswordHash(securityService.getHash(password));

        // Set roles
        List<Role> companyAdminRoles = rolesAndRightsDao.findCompanyAdminRoles();
        profile.setRoles(new HashSet<Role>(companyAdminRoles));

        // Persist
        userProfileDao.persist(profile);
    }
}
