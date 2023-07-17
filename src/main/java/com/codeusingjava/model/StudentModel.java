package com.codeusingjava.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "STUDENT")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentIdentifier")
    private long studentIdentifier;

    @Column(name = "udid")
    private Integer udid;

    @Column(name = "emisIdentifier")
    private String emisIdentifier;

    @Column(name = "studentFirstName")
    private String studentFirstName;

    @Column(name = "studentMiddleName")
    private String studentMiddleName;

    @Column(name = "studentLastName")
    private String studentLastName;

    @Column(name = "studentEmailAddress")
    private String studentEmailAddress;

    @Column(name = "studentContactNumber")
    private String studentContactNumber;

    @Column(name = "studentBirthMonthNumber")
    private Integer studentBirthMonthNumber;

    @Column(name = "studentBirthDayNumber")
    private Integer studentBirthDayNumber;

    @Column(name = "studentBirthYear")
    private String studentBirthYear;

    @Column(name = "studentGender")
    private String studentGender;

    @Column(name = "admissionMonthNumber")
    private Integer admissionMonthNumber;

    @Column(name = "admissionDayNumber")
    private Integer admissionDayNumber;

    @Column(name = "admissionYear")
    private String admissionYear;

    @Column(name = "admittedGrade")
    private String admittedGrade;

    @Column(name = "disabilityIndicator")
    private String disabilityIndicator;

    @Column(name = "fatherName")
    private String fatherName;

    @Column(name = "fatherOccupation")
    private String fatherOccupation;

    @Column(name = "fatherAnnualIncome")
    private BigDecimal fatherAnnualIncome;

    @Column(name = "motherName")
    private String motherName;

    @Column(name = "motherOccupation")
    private String motherOccupation;

    @Column(name = "motherAnnualIncome")
    private BigDecimal motherAnnualIncome;

    @Column(name = "studentAadharIdentifier")
    private String studentAadharIdentifier;

    @Column(name = "currentAddressLine1")
    private String currentAddressLine1;

    @Column(name = "currentAddressLine2")
    private String currentAddressLine2;

    @Column(name = "currentAddressLine3")
    private String currentAddressLine3;

    @Column(name = "currentAddressCity")
    private String currentAddressCity;

    @Column(name = "currentAddressState")
    private String currentAddressState;

    @Column(name = "currentAddressZipCode")
    private String currentAddressZipCode;

    @Column(name = "permanentAddressLine1")
    private String permanentAddressLine1;

    @Column(name = "permanentAddressLine2")
    private String permanentAddressLine2;

    @Column(name = "permanentAddressLine3")
    private String permanentAddressLine3;

    @Column(name = "permanentAddressCity")
    private String permanentAddressCity;

    @Column(name = "permanentAddressState")
    private String permanentAddressState;

    @Column(name = "permanentAddressZipCode")
    private String permanentAddressZipCode;

    @Column(name = "PRIMARY_CONTACT_NAME")
    private String primaryContactName;

    @Column(name = "PRIMARY_CONTACT_NUMBER")
    private String primaryContactNumber;

    @Column(name = "PRIMARY_CONTACT_RELATIONSHIP_TEXT")
    private String primaryContactRelationship;

    @Column(name = "SECONDARY_CONTACT_NAME")
    private String secondaryContactName;

    @Column(name = "SECONDARY_CONTACT_NUMBER")
    private String secondaryContactNumber;

    @Column(name = "SECONDARY_CONTACT_RELATIONSHIP_TEXT")
    private String secondaryContactRelationship;

    @Column(name = "PHYSICIAN_NAME")
    private String physicianName;

    @Column(name = "PHYSICIAN_PRIMARY_CONTACT_NUMBER")
    private String physicianPrimaryContactNumber;

    @Column(name = "PHYSICIAN_SECONDARY_CONTACT_NUMBER")
    private String physicianSecondaryContactNumber;

    @Column(name = "PREFERRED_EMERGENCY_HOSPITAL_NAME")
    private String preferredEmergencyHospital;

    @Column(name = "CURRENT_MEDICATION_DETAIL_TEXT")
    private String currentMedicationDetail;

    @Column(name = "MEDICAL_ALLERGY_DETAIL_TEXT")
    private String medicalAllergyDetail;

    @Column(name = "FOOD_ALLERGY_DETAIL_TEXT")
    private String foodAllergyDetail;

    @Column(name = "CHRONIC_HEALTH_ISSUE_DETAIL_TEXT")
    private String chronicHealthIssueDetail;

    @Column(name = "PREVIOUS_SCHOOL_NAME")
    private String previousSchoolName;

    @Column(name = "PREVIOUS_SCHOOL_CITY_NAME")
    private String previousSchoolCity;

    @Column(name = "PREVIOUS_SCHOOL_STATE_NAME")
    private String previousSchoolState;

    @Column(name = "PREVIOUS_SCHOOL_ADMISSION_DATE")
    private Date previousSchoolAdmissionDate;

    @Column(name = "PREVIOUS_SCHOOL_LAST_DATE")
    private Date previousSchoolLastDate;

    @Column(name = "ADDITIONAL_DETAILS_TEXT")
    private String additionalDetails;

    @Column(name = "BIRTH_CERTIFICATE_AVAILABLE_INDICATOR")
    private String birthCertificateAvailableIndicator;

    @Column(name = "AADHAR_ID_AVAILABLE_INDICATOR")
    private String aadharIdAvailableIndicator;

    @Column(name = "COMMUNITY_CERTIFICATE_APPLICABLE_INDICATOR")
    private String communityCertificateApplicableIndicator;

    @Column(name = "COMMUNITY_CERTIFICATE_AVAILABLE_INDICATOR")
    private String communityCertificateAvailableIndicator;

    @Column(name = "WHATSAPP_NUMBER")
    private String whatsappNumber;

    public long getStudentIdentifier() {
        return studentIdentifier;
    }

    public void setStudentIdentifier(int studentIdentifier) {
        this.studentIdentifier = studentIdentifier;
    }

    public Integer getUdid() {
        return udid;
    }

    public void setUdid(Integer udid) {
        this.udid = udid;
    }

    public String getEmisIdentifier() {
        return emisIdentifier;
    }

    public void setEmisIdentifier(String emisIdentifier) {
        this.emisIdentifier = emisIdentifier;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentEmailAddress() {
        return studentEmailAddress;
    }

    public void setStudentEmailAddress(String studentEmailAddress) {
        this.studentEmailAddress = studentEmailAddress;
    }

    public String getStudentContactNumber() {
        return studentContactNumber;
    }

    public void setStudentContactNumber(String studentContactNumber) {
        this.studentContactNumber = studentContactNumber;
    }

    public Integer getStudentBirthMonthNumber() {
        return studentBirthMonthNumber;
    }

    public void setStudentBirthMonthNumber(Integer studentBirthMonthNumber) {
        this.studentBirthMonthNumber = studentBirthMonthNumber;
    }

    public Integer getStudentBirthDayNumber() {
        return studentBirthDayNumber;
    }

    public void setStudentBirthDayNumber(Integer studentBirthDayNumber) {
        this.studentBirthDayNumber = studentBirthDayNumber;
    }

    public String getStudentBirthYear() {
        return studentBirthYear;
    }

    public void setStudentBirthYear(String studentBirthYear) {
        this.studentBirthYear = studentBirthYear;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public Integer getAdmissionMonthNumber() {
        return admissionMonthNumber;
    }

    public void setAdmissionMonthNumber(Integer admissionMonthNumber) {
        this.admissionMonthNumber = admissionMonthNumber;
    }

    public Integer getAdmissionDayNumber() {
        return admissionDayNumber;
    }

    public void setAdmissionDayNumber(Integer admissionDayNumber) {
        this.admissionDayNumber = admissionDayNumber;
    }

    public String getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(String admissionYear) {
        this.admissionYear = admissionYear;
    }

    public String getAdmittedGrade() {
        return admittedGrade;
    }

    public void setAdmittedGrade(String admittedGrade) {
        this.admittedGrade = admittedGrade;
    }

    public String getDisabilityIndicator() {
        return disabilityIndicator;
    }

    public void setDisabilityIndicator(String disabilityIndicator) {
        this.disabilityIndicator = disabilityIndicator;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public BigDecimal getFatherAnnualIncome() {
        return fatherAnnualIncome;
    }

    public void setFatherAnnualIncome(BigDecimal fatherAnnualIncome) {
        this.fatherAnnualIncome = fatherAnnualIncome;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public BigDecimal getMotherAnnualIncome() {
        return motherAnnualIncome;
    }

    public void setMotherAnnualIncome(BigDecimal motherAnnualIncome) {
        this.motherAnnualIncome = motherAnnualIncome;
    }

    public String getStudentAadharIdentifier() {
        return studentAadharIdentifier;
    }

    public void setStudentAadharIdentifier(String studentAadharIdentifier) {
        this.studentAadharIdentifier = studentAadharIdentifier;
    }

    public String getCurrentAddressLine1() {
        return currentAddressLine1;
    }

    public void setCurrentAddressLine1(String currentAddressLine1) {
        this.currentAddressLine1 = currentAddressLine1;
    }

    public String getCurrentAddressLine2() {
        return currentAddressLine2;
    }

    public void setCurrentAddressLine2(String currentAddressLine2) {
        this.currentAddressLine2 = currentAddressLine2;
    }

    public String getCurrentAddressLine3() {
        return currentAddressLine3;
    }

    public void setCurrentAddressLine3(String currentAddressLine3) {
        this.currentAddressLine3 = currentAddressLine3;
    }

    public String getCurrentAddressCity() {
        return currentAddressCity;
    }

    public void setCurrentAddressCity(String currentAddressCity) {
        this.currentAddressCity = currentAddressCity;
    }

    public String getCurrentAddressState() {
        return currentAddressState;
    }

    public void setCurrentAddressState(String currentAddressState) {
        this.currentAddressState = currentAddressState;
    }

    public String getCurrentAddressZipCode() {
        return currentAddressZipCode;
    }

    public void setCurrentAddressZipCode(String currentAddressZipCode) {
        this.currentAddressZipCode = currentAddressZipCode;
    }

    public String getPermanentAddressLine1() {
        return permanentAddressLine1;
    }

    public void setPermanentAddressLine1(String permanentAddressLine1) {
        this.permanentAddressLine1 = permanentAddressLine1;
    }

    public String getPermanentAddressLine2() {
        return permanentAddressLine2;
    }

    public void setPermanentAddressLine2(String permanentAddressLine2) {
        this.permanentAddressLine2 = permanentAddressLine2;
    }

    public String getPermanentAddressLine3() {
        return permanentAddressLine3;
    }

    public void setPermanentAddressLine3(String permanentAddressLine3) {
        this.permanentAddressLine3 = permanentAddressLine3;
    }

    public String getPermanentAddressCity() {
        return permanentAddressCity;
    }

    public void setPermanentAddressCity(String permanentAddressCity) {
        this.permanentAddressCity = permanentAddressCity;
    }

    public String getPermanentAddressState() {
        return permanentAddressState;
    }

    public void setPermanentAddressState(String permanentAddressState) {
        this.permanentAddressState = permanentAddressState;
    }

    public String getPermanentAddressZipCode() {
        return permanentAddressZipCode;
    }

    public void setPermanentAddressZipCode(String permanentAddressZipCode) {
        this.permanentAddressZipCode = permanentAddressZipCode;
    }

    public String getPrimaryContactName() {
        return primaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    public String getPrimaryContactNumber() {
        return primaryContactNumber;
    }

    public void setPrimaryContactNumber(String primaryContactNumber) {
        this.primaryContactNumber = primaryContactNumber;
    }

    public String getPrimaryContactRelationship() {
        return primaryContactRelationship;
    }

    public void setPrimaryContactRelationship(String primaryContactRelationship) {
        this.primaryContactRelationship = primaryContactRelationship;
    }

    public String getSecondaryContactName() {
        return secondaryContactName;
    }

    public void setSecondaryContactName(String secondaryContactName) {
        this.secondaryContactName = secondaryContactName;
    }

    public String getSecondaryContactNumber() {
        return secondaryContactNumber;
    }

    public void setSecondaryContactNumber(String secondaryContactNumber) {
        this.secondaryContactNumber = secondaryContactNumber;
    }

    public String getSecondaryContactRelationship() {
        return secondaryContactRelationship;
    }

    public void setSecondaryContactRelationship(String secondaryContactRelationship) {
        this.secondaryContactRelationship = secondaryContactRelationship;
    }

    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    public String getPhysicianPrimaryContactNumber() {
        return physicianPrimaryContactNumber;
    }

    public void setPhysicianPrimaryContactNumber(String physicianPrimaryContactNumber) {
        this.physicianPrimaryContactNumber = physicianPrimaryContactNumber;
    }

    public String getPhysicianSecondaryContactNumber() {
        return physicianSecondaryContactNumber;
    }

    public void setPhysicianSecondaryContactNumber(String physicianSecondaryContactNumber) {
        this.physicianSecondaryContactNumber = physicianSecondaryContactNumber;
    }

    public String getPreferredEmergencyHospital() {
        return preferredEmergencyHospital;
    }

    public void setPreferredEmergencyHospital(String preferredEmergencyHospital) {
        this.preferredEmergencyHospital = preferredEmergencyHospital;
    }

    public String getCurrentMedicationDetail() {
        return currentMedicationDetail;
    }

    public void setCurrentMedicationDetail(String currentMedicationDetail) {
        this.currentMedicationDetail = currentMedicationDetail;
    }

    public String getMedicalAllergyDetail() {
        return medicalAllergyDetail;
    }

    public void setMedicalAllergyDetail(String medicalAllergyDetail) {
        this.medicalAllergyDetail = medicalAllergyDetail;
    }

    public String getFoodAllergyDetail() {
        return foodAllergyDetail;
    }

    public void setFoodAllergyDetail(String foodAllergyDetail) {
        this.foodAllergyDetail = foodAllergyDetail;
    }

    public String getChronicHealthIssueDetail() {
        return chronicHealthIssueDetail;
    }

    public void setChronicHealthIssueDetail(String chronicHealthIssueDetail) {
        this.chronicHealthIssueDetail = chronicHealthIssueDetail;
    }

    public String getPreviousSchoolName() {
        return previousSchoolName;
    }

    public void setPreviousSchoolName(String previousSchoolName) {
        this.previousSchoolName = previousSchoolName;
    }

    public String getPreviousSchoolCity() {
        return previousSchoolCity;
    }

    public void setPreviousSchoolCity(String previousSchoolCity) {
        this.previousSchoolCity = previousSchoolCity;
    }

    public String getPreviousSchoolState() {
        return previousSchoolState;
    }

    public void setPreviousSchoolState(String previousSchoolState) {
        this.previousSchoolState = previousSchoolState;
    }

    public Date getPreviousSchoolAdmissionDate() {
        return previousSchoolAdmissionDate;
    }

    public void setPreviousSchoolAdmissionDate(Date previousSchoolAdmissionDate) {
        this.previousSchoolAdmissionDate = previousSchoolAdmissionDate;
    }

    public Date getPreviousSchoolLastDate() {
        return previousSchoolLastDate;
    }

    public void setPreviousSchoolLastDate(Date previousSchoolLastDate) {
        this.previousSchoolLastDate = previousSchoolLastDate;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getBirthCertificateAvailableIndicator() {
        return birthCertificateAvailableIndicator;
    }

    public void setBirthCertificateAvailableIndicator(String birthCertificateAvailableIndicator) {
        this.birthCertificateAvailableIndicator = birthCertificateAvailableIndicator;
    }

    public String getAadharIdAvailableIndicator() {
        return aadharIdAvailableIndicator;
    }

    public void setAadharIdAvailableIndicator(String aadharIdAvailableIndicator) {
        this.aadharIdAvailableIndicator = aadharIdAvailableIndicator;
    }

    public String getCommunityCertificateApplicableIndicator() {
        return communityCertificateApplicableIndicator;
    }

    public void setCommunityCertificateApplicableIndicator(String communityCertificateApplicableIndicator) {
        this.communityCertificateApplicableIndicator = communityCertificateApplicableIndicator;
    }

    public String getCommunityCertificateAvailableIndicator() {
        return communityCertificateAvailableIndicator;
    }

    public void setCommunityCertificateAvailableIndicator(String communityCertificateAvailableIndicator) {
        this.communityCertificateAvailableIndicator = communityCertificateAvailableIndicator;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public StudentModel() {
    }
}
