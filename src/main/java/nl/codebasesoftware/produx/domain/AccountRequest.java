package nl.codebasesoftware.produx.domain;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 20-4-13
 * Time: 16:25
 */
@Entity
public class AccountRequest implements DomainObject {

    private Long id;
    private String firstName;
    private String preposition;
    private String lastName;
    private String email;
    private String phone;
    private String companyName;
    private String tradeNumber;
    private String message;
    private String securityCode;
    private Calendar requestDate;
    private boolean evaluated;
    private Boolean granted;

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
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
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

    @Column(nullable = true)
    public Boolean isGranted() {
        return granted;
    }

    public void setGranted(Boolean granted) {
        this.granted = granted;
    }

    @Transient
    public String getFullNameFormal(){
        return preposition != null ? String.format("%s, %s  %s", lastName, firstName, preposition) : String.format("%s, %s", lastName, firstName);
    }
}
