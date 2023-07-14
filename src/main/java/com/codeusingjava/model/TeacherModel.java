package com.codeusingjava.model;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class TeacherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="staff_Id")
    private long staffId;
    @Column(name="staff_Name")
    private String staffName;

    @Column(name="Primary_Contact_Number")
    private long primaryContactNumber ;

    @Column(name="Secondary_Contact_Number")
    private long secondaryContactNumber ;

    @Column(name="address")
    private String address ;

    @Column(name="emailID")
    private String emailId ;

    @Column(name="Whatsapp_Number")
    private long whatsappNumber ;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPrimaryContactNumber() {
        return primaryContactNumber;
    }

    public void setPrimaryContactNumber(long primaryContactNumber) {
        this.primaryContactNumber = primaryContactNumber;
    }

    public long getSecondaryContactNumber() {
        return secondaryContactNumber;
    }

    public void setSecondaryContactNumber(long secondaryContactNumber) {
        this.secondaryContactNumber = secondaryContactNumber;
    }

    public long getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(long whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public TeacherModel() {
    }
}
