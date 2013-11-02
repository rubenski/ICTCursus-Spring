package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.AccountRequestEntityDTO;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:25
 */
@Entity
public class AccountRequest implements DomainEntity {

    private Long id;

    private String companyAddress;
    private String companyCity;
    private String country;
    private String companyEmail;
    private String companyPhone;
    private String companyZipCode;
    private String tradeNumber;
    private String vatNumber;
    private String companyPrefix;

    private String firstName;
    private String preposition;
    private String lastName;
    private String email;
    private String phone;
    private String companyName;
    private String userMessage;
    private Calendar requestDate;
    private boolean evaluated;
    private Boolean granted;
    private String adminMessage;

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
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPreposition() {
        return preposition;
    }

    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Lob
    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getCompanyPrefix() {
        return companyPrefix;
    }

    public void setCompanyPrefix(String companyPrefix) {
        this.companyPrefix = companyPrefix;
    }

    @Column(nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(nullable = false)
    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Calendar requestDate) {
        this.requestDate = requestDate;
    }

    @Column(nullable = false)
    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    @Column(nullable = false)
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Column(nullable = false)
    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    @Column(nullable = false)
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Column(nullable = false)
    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(nullable = true)
    public Boolean isGranted() {
        return granted;
    }

    public void setGranted(Boolean granted) {
        this.granted = granted;
    }

    public String getCompanyZipCode() {
        return companyZipCode;
    }

    public void setCompanyZipCode(String companyZipCode) {
        this.companyZipCode = companyZipCode;
    }

    @Lob
    public String getAdminMessage() {
        return adminMessage;
    }

    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }

    @Override
    @Transient
    public AccountRequestEntityDTO toDTO() {
        AccountRequestEntityDTO dto = new AccountRequestEntityDTO();
        dto.setId(id);
        dto.setAdminMessage(adminMessage);
        dto.setCompanyAddress(companyAddress);
        dto.setCompanyCity(companyCity);
        dto.setCompanyEmail(companyEmail);
        dto.setCompanyName(companyName);
        dto.setCompanyPhone(companyPhone);
        dto.setCompanyZipCode(companyZipCode);
        dto.setCountry(country);
        dto.setEmail(email);
        dto.setEvaluated(evaluated);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setPhone(phone);
        dto.setGranted(granted);
        dto.setPreposition(preposition);
        dto.setRequestDate(requestDate);
        dto.setTradeNumber(tradeNumber);
        dto.setUserMessage(userMessage);
        dto.setVatNumber(vatNumber);
        dto.setCompanyPrefix(companyPrefix);
        return dto;
    }

    @Transient
    public String getFullNameFormal() {
        return preposition != null ? String.format("%s, %s  %s", lastName, firstName, preposition) : String.format("%s, %s", lastName, firstName);
    }

    @Transient
    public String getFullNameInformal() {
        return preposition != null ? String.format("%s %s %s", firstName, preposition, lastName) : String.format("%s %s", firstName, lastName);
    }

    @Transient
    public void reject(String rejectMessage) {
        this.granted = false;
        this.evaluated = true;
        this.adminMessage = rejectMessage;
    }

    @Transient
    public void grant() {
        granted = true;
        evaluated = true;
    }

    @Transient
    public String getFormattedDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(requestDate.getTime());
    }
}
