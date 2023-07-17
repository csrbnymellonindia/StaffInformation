package com.codeusingjava.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teacher_family_financial_information")
public class TeacherFamilyFinancialInformation {

    @Id
    @Column(name="staff_id")
    private Long staffId;

    @Column(name="bank_name")
    private String bankName;
    @Column(name="bank_IFSC_code")
    private String bankIFSCCode;

    @Column(name="bank_account_number")
    private long bankAccountNumber;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankIFSCCode() {
        return bankIFSCCode;
    }

    public void setBankIFSCCode(String bankIFSCCode) {
        this.bankIFSCCode = bankIFSCCode;
    }

    public long getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(long bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public TeacherFamilyFinancialInformation() {
    }
}
