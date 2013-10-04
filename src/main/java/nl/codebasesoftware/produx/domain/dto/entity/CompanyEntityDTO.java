package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.dto.listing.ListingCompanyDTO;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 15-8-13
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public class CompanyEntityDTO extends ListingCompanyDTO {


    private String email;
    private String address;
    private String zipCode;
    private String description;
    private String vatNumber;
    private String tradeNumber;
    private String phone;
    private String city;
    private String country;
    private Integer budgetTriggerAmount;
    private String courseRequestEmailAddress;
    private boolean allCoursesDeactivated;
    private byte[] normalLogo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getBudgetTriggerAmount() {
        return budgetTriggerAmount;
    }

    public void setBudgetTriggerAmount(Integer budgetTriggerAmount) {
        this.budgetTriggerAmount = budgetTriggerAmount;
    }

    public String getCourseRequestEmailAddress() {
        return courseRequestEmailAddress;
    }

    public void setCourseRequestEmailAddress(String courseRequestEmailAddress) {
        this.courseRequestEmailAddress = courseRequestEmailAddress;
    }

    public boolean isAllCoursesDeactivated() {
        return allCoursesDeactivated;
    }

    public void setAllCoursesDeactivated(boolean allCoursesDeactivated) {
        this.allCoursesDeactivated = allCoursesDeactivated;
    }

    public void setNormalLogo(byte[] normalLogo) {
        this.normalLogo = normalLogo;
    }

    public boolean hasLogo(){
        return normalLogo != null;
    }
}
