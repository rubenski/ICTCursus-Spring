package nl.codebasesoftware.produx.formdata;

import java.io.Serializable;

/**
 * User: rvanloen
 * Date: 27-12-12
 * Time: 11:38
 */
public class BindableCompany implements Serializable {

    private Long id;
    private String email;
    private String name;
    private String address;
    private String zipCode;
    private String description;
    private String vatNumber;
    private String chamberOfCommerceNumber;
    private String phone;
    private String logoFileName;
    private String logoFileExtension;
    private String logoFileType;
    private String city;
    private String country;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChamberOfCommerceNumber() {
        return chamberOfCommerceNumber;
    }

    public void setChamberOfCommerceNumber(String chamberOfCommerceNumber) {
        this.chamberOfCommerceNumber = chamberOfCommerceNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLogoFileExtension() {
        return logoFileExtension;
    }

    public String getLogoFileType() {
        return logoFileType;
    }

    public void setLogoFileType(String logoFileType) {
        this.logoFileType = logoFileType;
    }

    public void setLogoFileExtension(String logoFileExtension) {
        this.logoFileExtension = logoFileExtension;
    }


}
