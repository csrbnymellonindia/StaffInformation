package com.codeusingjava.service;

import com.codeusingjava.model.TeacherFamilyFinancialInformation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TeacherFamilyFinancialInformationService {

    List<TeacherFamilyFinancialInformation> findAllTeacherFamilyFinancialInformation();

    Optional<TeacherFamilyFinancialInformation> findTeacherFamilyFinancialInformationById(Long staffId);

    ResponseEntity<String> addTeacherFamilyFinancialInformation(TeacherFamilyFinancialInformation teacherFamilyFinancialInformation);

    ResponseEntity<String> updateTeacherFamilyFinancialInformation(Long staffId,TeacherFamilyFinancialInformation teacherFamilyFinancialInformation);

    ResponseEntity<String> deleteTeacherFamilyFinancialInformation(Long staffId);
}
