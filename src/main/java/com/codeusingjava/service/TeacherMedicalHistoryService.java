package com.codeusingjava.service;

import com.codeusingjava.model.TeacherMedicalHistoryEntity;
import com.codeusingjava.model.TeacherModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TeacherMedicalHistoryService {

    List<TeacherMedicalHistoryEntity> findAllTeachersMedicalHistory();

    Optional<TeacherMedicalHistoryEntity> findTeacherMedicalHistoryById(Long id);
    ResponseEntity<String> addTeacherMedicalHistory(TeacherMedicalHistoryEntity teacherMedicalHistoryEntity);
    ResponseEntity<String> updateTeacherMedicalHistory(Long staffId,TeacherMedicalHistoryEntity teacherMedicalHistoryEntity);
    ResponseEntity<String> deleteTeacherMedicalHistory(Long staffId);
}
