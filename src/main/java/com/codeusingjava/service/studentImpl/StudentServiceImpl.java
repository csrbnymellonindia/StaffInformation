package com.codeusingjava.service.studentImpl;

import com.codeusingjava.model.StudentModel;
import com.codeusingjava.repository.StudentRepository;
import com.codeusingjava.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentModel> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<StudentModel> findStudentById(long studentIdentifier) {
        return studentRepository.findById(studentIdentifier);
    }

    @Override
    public ResponseEntity<String> addStudent(StudentModel student) {
        if (validateStudent(student)) {
            studentRepository.save(student);
           return ResponseEntity.ok().body("Student added successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid student details");
        }
    }

    @Override
    public ResponseEntity<String> updateStudent(int studentIdentifier, StudentModel student) {
        if (validateStudent(student)) {
            Optional<StudentModel> optionalStudent = studentRepository.findById(Long.valueOf(studentIdentifier));
                StudentModel existingStudent = optionalStudent.get();
                existingStudent.setUdid(student.getUdid());
                existingStudent.setEmisIdentifier(student.getEmisIdentifier());
                existingStudent.setStudentFirstName(student.getStudentFirstName());
                existingStudent.setStudentMiddleName(student.getStudentMiddleName());
                existingStudent.setStudentLastName(student.getStudentLastName());
                // Update other properties as needed

                studentRepository.save(existingStudent);
                return ResponseEntity.ok().body("Student updated successfully!");

        } else {
            return ResponseEntity.badRequest().body("Invalid student details");
        }
    }

    @Override
    public ResponseEntity<String> deleteStudent(int studentIdentifier) {
        studentRepository.deleteById(Long.valueOf(studentIdentifier));
        return ResponseEntity.ok().body("Student deleted successfully!");
    }

    private boolean validateStudent(StudentModel student) {
        // Validate STUDENT_IDENTIFIER
        if (student.getStudentIdentifier() <= 0) {
            return false;
        }

        // Validate UDID (if present)
        if (student.getUdid() != null && student.getUdid() <= 0) {
            return false;
        }

        // Validate EMIS_IDENTIFIER (if present)
        if (student.getEmisIdentifier() != null && student.getEmisIdentifier().isEmpty()) {
            return false;
        }

        // Validate STUDENT_FIRST_NAME
        if (student.getStudentFirstName() == null || student.getStudentFirstName().isEmpty()) {
            return false;
        }

        // Validate STUDENT_MIDDLE_NAME (if present)
        if (student.getStudentMiddleName() != null && student.getStudentMiddleName().isEmpty()) {
            return false;
        }

        // Validate STUDENT_LAST_NAME
        if (student.getStudentLastName() == null || student.getStudentLastName().isEmpty()) {
            return false;
        }

        // Validate STUDENT_EMAIL_ADDRESS_TEXT (if present)
        if (student.getStudentEmailAddress() != null && !isValidEmail(student.getStudentEmailAddress())) {
            return false;
        }

        // Validate STUDENT_CONTACT_NUMBER (if present)
        if (student.getStudentContactNumber() != null && !isValidContactNumber(student.getStudentContactNumber())) {
            return false;
        }

        // Validate STUDENT_BIRTH_MONTH_NUMBER (if present)
        if (student.getStudentBirthMonthNumber() != null && !isValidMonth(student.getStudentBirthMonthNumber())) {
            return false;
        }

        // Validate STUDENT_BIRTH_DAY_NUMBER (if present)
        if (student.getStudentBirthDayNumber() != null && !isValidDay(student.getStudentBirthDayNumber())) {
            return false;
        }

        // Validate STUDENT_BIRTH_YEAR_TEXT (if present)
        if (student.getStudentBirthYear() != null && !isValidYear(student.getStudentBirthYear())) {
            return false;
        }

        // Validate STUDENT_GENDER (if present)
        if (student.getStudentGender() != null && !isValidGender(student.getStudentGender())) {
            return false;
        }

        // Validate ADMISSION_MONTH_NUMBER (if present)
        if (student.getAdmissionMonthNumber() != null && !isValidMonth(student.getAdmissionMonthNumber())) {
            return false;
        }

        // Validate ADMISSION_DAY_NUMBER (if present)
        if (student.getAdmissionDayNumber() != null && !isValidDay(student.getAdmissionDayNumber())) {
            return false;
        }

        // Validate ADMISSION_YEAR (if present)
        if (student.getAdmissionYear() != null && !isValidYear(student.getAdmissionYear())) {
            return false;
        }

        // Validate ADMITTED_GRADE_TEXT (if present)
        if (student.getAdmittedGrade() == null || student.getAdmittedGrade().isEmpty()) {
            return false;
        }

        // Validate DISABILITY_INDICATOR (if present)
        if (student.getDisabilityIndicator() != null) {
            return false;
        }

        // Validate FATHER_NAME (if present)
        if (student.getFatherName() != null && student.getFatherName().isEmpty()) {
            return false;
        }

        // Validate FATHER_OCCUPATION_TEXT (if present)
        if (student.getFatherOccupation() != null && student.getFatherOccupation().isEmpty()) {
            return false;
        }

        // Validate FATHER_ANNUAL_INCOME_AMOUNT (if present)
        if (student.getFatherAnnualIncome() != null) {
            return false;
        }

        // Validate MOTHER_NAME (if present)
        if (student.getMotherName() != null && student.getMotherName().isEmpty()) {
            return false;
        }

        // Validate MOTHER_OCCUPATION_TEXT (if present)
        if (student.getMotherOccupation() != null && student.getMotherOccupation().isEmpty()) {
            return false;
        }

        // Validate MOTHER_ANNUAL_INCOME_AMOUNT (if present)
        if (student.getMotherAnnualIncome() != null) {
            return false;
        }

        // Validate STUDENT_AADHAR_IDENTIFER (if present)
        if (student.getStudentAadharIdentifier() != null && student.getStudentAadharIdentifier().isEmpty()) {
            return false;
        }

        // Validate CURRENT_ADDRESS_LINE_1_TEXT (if present)
        if (student.getCurrentAddressLine1() != null && student.getCurrentAddressLine1().isEmpty()) {
            return false;
        }

        // Validate CURRENT_ADDRESS_LINE_2_TEXT (if present)
        if (student.getCurrentAddressLine2() != null && student.getCurrentAddressLine2().isEmpty()) {
            return false;
        }

        // Validate CURRENT_ADDRESS_LINE_3_TEXT (if present)
        if (student.getCurrentAddressLine3() != null && student.getCurrentAddressLine3().isEmpty()) {
            return false;
        }

        // Validate CURRENT_ADDRESS_CITY_NAME (if present)
        if (student.getCurrentAddressCity() != null && student.getCurrentAddressCity().isEmpty()) {
            return false;
        }

        // Validate CURRENT_ADDRESS_STATE_NAME (if present)
        if (student.getCurrentAddressState() != null && student.getCurrentAddressState().isEmpty()) {
            return false;
        }

        // Validate CURRENT_ADDRESS_ZIP_CODE (if present)
        if (student.getCurrentAddressZipCode() != null && student.getCurrentAddressZipCode().isEmpty()) {
            return false;
        }

        // Validate PERMANENT_ADDRESS_LINE_1_TEXT (if present)
        if (student.getPermanentAddressLine1() != null && student.getPermanentAddressLine1().isEmpty()) {
            return false;
        }

        // Validate PERMANENT_ADDRESS_LINE_2_TEXT (if present)
        if (student.getPermanentAddressLine2() != null && student.getPermanentAddressLine2().isEmpty()) {
            return false;
        }

        // Validate PERMANENT_ADDRESS_LINE_3_TEXT (if present)
        if (student.getPermanentAddressLine3() != null && student.getPermanentAddressLine3().isEmpty()) {
            return false;
        }

        // Validate PERMANENT_ADDRESS_CITY_NAME (if present)
        if (student.getPermanentAddressCity() != null && student.getPermanentAddressCity().isEmpty()) {
            return false;
        }

        // Validate PERMANENT_ADDRESS_STATE_NAME (if present)
        if (student.getPermanentAddressState() != null && student.getPermanentAddressState().isEmpty()) {
            return false;
        }

        // Validate PERMANENT_ADDRESS_ZIP_CODE (if present)
        if (student.getPermanentAddressZipCode() != null && student.getPermanentAddressZipCode().isEmpty()) {
            return false;
        }

        // Validate PRIMARY_CONTACT_NAME (if present)
        if (student.getPrimaryContactName() != null && student.getPrimaryContactName().isEmpty()) {
            return false;
        }

        // Validate PRIMARY_CONTACT_NUMBER (if present)
        if (student.getPrimaryContactNumber() != null && !isValidContactNumber(student.getPrimaryContactNumber())) {
            return false;
        }

        // Validate PRIMARY_CONTACT_RELATIONSHIP_TEXT (if present)
        if (student.getPrimaryContactRelationship() != null && student.getPrimaryContactRelationship().isEmpty()) {
            return false;
        }

        // Validate SECONDARY_CONTACT_NAME (if present)
        if (student.getSecondaryContactName() != null && student.getSecondaryContactName().isEmpty()) {
            return false;
        }

        // Validate SECONDARY_CONTACT_NUMBER (if present)
        if (student.getSecondaryContactNumber() != null && !isValidContactNumber(student.getSecondaryContactNumber())) {
            return false;
        }

        // Validate SECONDARY_CONTACT_RELATIONSHIP_TEXT (if present)
        if (student.getSecondaryContactRelationship() != null && student.getSecondaryContactRelationship().isEmpty()) {
            return false;
        }

        // Validate PHYSICIAN_NAME (if present)
        if (student.getPhysicianName() != null && student.getPhysicianName().isEmpty()) {
            return false;
        }

        // Validate PHYSICIAN_PRIMARY_CONTACT_NUMBER (if present)
        if (student.getPhysicianPrimaryContactNumber() != null && !isValidContactNumber(student.getPhysicianPrimaryContactNumber())) {
            return false;
        }

        // Validate PHYSICIAN_SECONDARY_CONTACT_NUMBER (if present)
        if (student.getPhysicianSecondaryContactNumber() != null && !isValidContactNumber(student.getPhysicianSecondaryContactNumber())) {
            return false;
        }

        // Validate PREFERRED_EMERGENCY_HOSPITAL_NAME (if present)
        if (student.getPreferredEmergencyHospital() != null && student.getPreferredEmergencyHospital().isEmpty()) {
            return false;
        }

        // Validate CURRENT_MEDICATION_DETAIL_TEXT (if present)
        if (student.getCurrentMedicationDetail() != null && student.getCurrentMedicationDetail().isEmpty()) {
            return false;
        }

        // Validate MEDICAL_ALLERGY_DETAIL_TEXT (if present)
        if (student.getMedicalAllergyDetail() != null && student.getMedicalAllergyDetail().isEmpty()) {
            return false;
        }

        // Validate FOOD_ALLERGY_DETAIL_TEXT (if present)
        if (student.getFoodAllergyDetail() != null && student.getFoodAllergyDetail().isEmpty()) {
            return false;
        }

        // Validate CHRONIC_HEALTH_ISSUE_DETAIL_TEXT (if present)
        if (student.getChronicHealthIssueDetail() != null && student.getChronicHealthIssueDetail().isEmpty()) {
            return false;
        }

        // Validate PREVIOUS_SCHOOL_NAME (if present)
        if (student.getPreviousSchoolName() != null && student.getPreviousSchoolName().isEmpty()) {
            return false;
        }

        // Validate PREVIOUS_SCHOOL_CITY_NAME (if present)
        if (student.getPreviousSchoolCity() != null && student.getPreviousSchoolCity().isEmpty()) {
            return false;
        }

        // Validate PREVIOUS_SCHOOL_STATE_NAME (if present)
        if (student.getPreviousSchoolState() != null && student.getPreviousSchoolState().isEmpty()) {
            return false;
        }

        // Validate PREVIOUS_SCHOOL_ADMISSION_DATE (if present)
        if (student.getPreviousSchoolAdmissionDate() != null) {
            return false;
        }

        // Validate PREVIOUS_SCHOOL_LAST_DATE (if present)
        if (student.getPreviousSchoolLastDate() != null) {
            return false;
        }

        // Validate ADDITIONAL_DETAILS_TEXT (if present)
        if (student.getAdditionalDetails() != null && student.getAdditionalDetails().isEmpty()) {
            return false;
        }

        // Validate BIRTH_CERTIFICATE_AVAILABLE_INDICATOR (if present)
        if (student.getBirthCertificateAvailableIndicator() != null) {
            return false;
        }

        // Validate AADHAR_ID_AVAILABLE_INDICATOR (if present)
        if (student.getAadharIdAvailableIndicator() != null) {
            return false;
        }

        // Validate COMMUNITY_CERTIFICATE_APPLICABLE_INDICATOR (if present)
        if (student.getCommunityCertificateApplicableIndicator() != null) {
            return false;
        }

        // Validate COMMUNITY_CERTIFICATE_AVAILABLE_INDICATOR (if present)
        if (student.getCommunityCertificateAvailableIndicator() != null) {
            return false;
        }

        // Validate WHATSAPP_NUMBER (if present)
        if (student.getWhatsappNumber() != null && !isValidContactNumber(student.getWhatsappNumber())) {
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean isValidContactNumber(String contactNumber) {
        return(contactNumber.length()==10);
    }

    private boolean isValidMonth(Integer month) {
        return(month>0 && month<=12);
    }

    private boolean isValidDay(Integer day) {
        return(day>0 && day<=31);
    }

    private boolean isValidYear(String year) {
        return(year.length()==4);
    }

    private boolean isValidGender(String gender) {
        return(gender.equals("Male") || gender.equals("Female"));
    }


}
