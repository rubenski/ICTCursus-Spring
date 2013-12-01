package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.ProductSettingsEntityDTO;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCompanyDTO;
import nl.codebasesoftware.produx.domain.support.CourseListingType;
import nl.codebasesoftware.produx.formdata.BindableCompany;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 20:55
 */
@Entity
public class Company implements DomainEntity, Serializable {

    private Long id;
    private String email;
    private String name;
    private String address;
    private String zipCode;
    private String description;
    private String vatNumber;
    private String tradeNumber;
    private String phone;
    private String city;
    private String country;
    private Set<Course> courses = new HashSet<Course>();
    private byte[] normalLogo;
    private byte[] smallLogo;
    private Set<UserProfile> users;
    private Integer budgetTriggerAmount;
    private String courseRequestEmailAddress;
    private boolean allCoursesDeactivated;
    private String companyPrefix;
    private ProductSettings productSettings;


    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false, unique = true)
    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    @Column(nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, unique = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(nullable = false, unique = true)
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    @Column(nullable = false)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Lob
    public byte[] getNormalLogo() {
        return normalLogo;
    }

    public void setNormalLogo(byte[] normalLogo) {
        this.normalLogo = normalLogo;
    }

    @Lob
    public byte[] getSmallLogo() {
        return smallLogo;
    }

    public void setSmallLogo(byte[] smallLogo) {
        this.smallLogo = smallLogo;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    public Set<UserProfile> getUsers() {
        return users;
    }

    public void setUsers(Set<UserProfile> users) {
        this.users = users;
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

    @Column(columnDefinition="tinyint(1) default 0")
    public boolean isAllCoursesDeactivated() {
        return allCoursesDeactivated;
    }

    public void setAllCoursesDeactivated(boolean allCoursesDeactivated) {
        this.allCoursesDeactivated = allCoursesDeactivated;
    }

    @Column(nullable = true, unique = true)
    public String getCompanyPrefix() {
        return companyPrefix;
    }

    public void setCompanyPrefix(String companyPrefix) {
        this.companyPrefix = companyPrefix;
    }

    public ProductSettings getProductSettings() {
        return productSettings;
    }

    public void setProductSettings(ProductSettings productSettings) {
        this.productSettings = productSettings;
    }



    @Transient
    public boolean hasLogo() {
        return normalLogo != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!id.equals(company.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public ListingCompanyDTO toListingCompanyDTO() {
        ListingCompanyDTO dto = new ListingCompanyDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setLogo(hasLogo());
        return dto;
    }

    public CompanyEntityDTO toDTO() {
        CompanyEntityDTO companyDTO = new CompanyEntityDTO();
        companyDTO.setName(name);
        companyDTO.setAddress(address);
        companyDTO.setAllCoursesDeactivated(allCoursesDeactivated);
        companyDTO.setBudgetTriggerAmount(budgetTriggerAmount);
        companyDTO.setCity(city);
        companyDTO.setCountry(country);
        companyDTO.setCourseRequestEmailAddress(courseRequestEmailAddress);
        companyDTO.setDescription(description);
        companyDTO.setEmail(email);
        companyDTO.setId(id);
        companyDTO.setTradeNumber(tradeNumber);
        companyDTO.setNormalLogo(normalLogo);
        companyDTO.setSmallLogo(smallLogo);
        companyDTO.setPhone(phone);
        companyDTO.setVatNumber(vatNumber);
        companyDTO.setZipCode(zipCode);
        companyDTO.setLogo(hasLogo());
        companyDTO.setCompanyPrefix(companyPrefix);

        ProductSettingsEntityDTO productSettings = new ProductSettingsEntityDTO();
        productSettings.setCompanyInfoActive(productSettings.isCompanyInfoActive());
        productSettings.setCourseListingType(productSettings.getCourseListingType());
        productSettings.setExternalCourseLinksActive(productSettings.isExternalCourseLinksActive());
        companyDTO.setProductSettings(productSettings);


        return companyDTO;
    }
}
