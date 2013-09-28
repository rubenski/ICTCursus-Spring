package nl.codebasesoftware.produx.service.business;

import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.UserProfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: rvanloen
 * Date: 24-4-13
 * Time: 21:45
 */


// TODO: This is a weird object, because it doesn't properly represent an account request's state after it has been granted. When a request
// is turend into a company and userprofile the boolean fields are representing the current situation, instead of the situation when the
// request was still uunevaluated
public class BasicAccountRequestEvaluation {

    private AccountRequest request;
    private Collection<Company> existingCompanies;
    private Collection<UserProfile> existingProfiles;


    private boolean userEmailExists;
    private boolean companyVatExists;
    private boolean companyTradeNumberExists;
    private boolean companyNameExists;
    private boolean companyEmailExists;
    private List<Company> potentiallyExistingCompanies = new ArrayList<Company>();
    private UserProfile existingUserProfile;

    public BasicAccountRequestEvaluation(AccountRequest request, Collection<Company> existingCompanies, Collection<UserProfile> existingProfiles) {
        this.request = request;
        this.existingCompanies = existingCompanies;
        this.existingProfiles = existingProfiles;

        evaluateCompanyFields();
        evaluateUserProfileFields();
    }

    private void evaluateCompanyFields() {

        for (Company company : existingCompanies) {

            if (company.getEmail().equals(request.getCompanyEmail())) {
                companyEmailExists = true;
            }

            if (company.getName().equals(request.getCompanyName())) {
                companyEmailExists = true;
            }

            if (company.getTradeNumber().equals(request.getTradeNumber())) {
                companyTradeNumberExists = true;
            }

            if (company.getVatNumber().equals(request.getVatNumber())) {
                companyVatExists = true;
            }

            if (company.getName().equals(request.getCompanyName())) {
                companyNameExists = true;
            }

            if (companyEmailExists || companyNameExists || companyTradeNumberExists || companyVatExists) {
                potentiallyExistingCompanies.add(company);
            }
        }
    }

    private void evaluateUserProfileFields() {

        for (UserProfile profile : existingProfiles) {
            if (profile.getEmail().equals(request.getEmail())) {
                userEmailExists = true;
                existingUserProfile = profile;
            }
        }
    }

    public boolean getCompanyEmailExists() {
        return companyEmailExists;
    }

    public boolean getCompanyNameExists() {
        return companyNameExists;
    }

    public boolean getCompanyVatExists() {
        return companyVatExists;
    }

    public boolean getCompanyTradeNumberExists() {
        return companyTradeNumberExists;
    }

    public UserProfile getExistingUserProfile() {
        return existingUserProfile;
    }

    public List<Company> getPotentiallyExistingCompanies() {
        return potentiallyExistingCompanies;
    }

    public AccountRequest getRequest() {
        return request;
    }

    public boolean getUserEmailExists() {
        return userEmailExists;
    }

    public boolean isOk() {
        return (!request.isEvaluated() && potentiallyExistingCompanies.size() == 0 && existingUserProfile == null) ||
                (request.isEvaluated() && !request.isGranted());
    }
}
