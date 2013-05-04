package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.formdata.BindableCompany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 20:55
 */
@Entity
public class Company implements DomainObject {

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

    public boolean isAllCoursesDeactivated() {
        return allCoursesDeactivated;
    }

    public void setAllCoursesDeactivated(boolean allCoursesDeactivated) {
        this.allCoursesDeactivated = allCoursesDeactivated;
    }

    @Transient
    public BindableCompany toBindableCompany() {
        BindableCompany bindableCompany = new BindableCompany();

        bindableCompany.setAddress(address);
        bindableCompany.setChamberOfCommerceNumber(tradeNumber);
        bindableCompany.setCity(city);
        bindableCompany.setCountry(country);
        bindableCompany.setDescription(description);
        bindableCompany.setEmail(email);
        bindableCompany.setId(id);
        bindableCompany.setName(name);
        bindableCompany.setPhone(phone);
        bindableCompany.setVatNumber(vatNumber);
        bindableCompany.setZipCode(zipCode);

        return bindableCompany;
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
}
