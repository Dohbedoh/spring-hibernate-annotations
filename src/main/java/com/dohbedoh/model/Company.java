package com.dohbedoh.model;

import javax.persistence.*;

/**
 * Simple Company: A name, a list of employees.
 * <p>
 * Created by Allan on 1/10/2015.
 */
@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "COMPANY_ID", precision = 5, scale = 0)
    private int companyId;

    @Column(name = "COMPANY_NAME", length = 32, nullable = false)
    private String companyName;

    @Column(name = "COMPANY_ADDRESS_ID", precision = 5, scale = 0, nullable = false)
    private int addressId;

    @Column(name = "COMPANY_CONTACT_ID", precision = 5, scale = 0, nullable = false)
    private int contactId;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
