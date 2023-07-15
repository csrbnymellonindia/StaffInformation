package com.hackathon.StaffInformation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="STAFF")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long staffId;

    @Column(name="staff_name")
    private String staffName;

    @Column(name="primary_contact_number")
    private Long primaryContactNumber;

    @Column(name="secondary_contact_number")
    private Long secondaryContactNumber;
    
    @Column(name="address_text")
    private String addressText;

    @Column(name="email_identifier")
    private String emailIdentifier;

    @Column(name="whatsapp_number")
    private Long whatsappNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "staff_users",
            joinColumns = @JoinColumn(name = "ID"),
            inverseJoinColumns = @JoinColumn(name = "username")
    )
    private User user;


    public Long getStaffId() {
        return this.staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getPrimaryContactNumber() {
        return this.primaryContactNumber;
    }

    public void setPrimaryContactNumber(Long primaryContactNumber) {
        this.primaryContactNumber = primaryContactNumber;
    }

    public Long getSecondaryContactNumber() {
        return this.secondaryContactNumber;
    }

    public void setSecondaryContactNumber(Long secondaryContactNumber) {
        this.secondaryContactNumber = secondaryContactNumber;
    }

    public String getAddressText() {
        return this.addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    public String getEmailIdentifier() {
        return this.emailIdentifier;
    }

    public void setEmailIdentifier(String emailIdentifier) {
        this.emailIdentifier = emailIdentifier;
    }

    public Long getWhatsappNumber() {
        return this.whatsappNumber;
    }

    public void setWhatsappNumber(Long whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    public Staff() {
    }
    
}
