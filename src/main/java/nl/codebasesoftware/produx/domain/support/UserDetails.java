package nl.codebasesoftware.produx.domain.support;

import nl.codebasesoftware.produx.domain.Company;

/**
 * User: rvanloen
 * Date: 26-12-12
 * Time: 0:37
 */
public class UserDetails {
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
