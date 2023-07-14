package com.codeusingjava.service;

import com.codeusingjava.model.TeacherModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<TeacherModel> findAllTeachers();

    Optional<TeacherModel> findById(Long staffId);
    ResponseEntity<String> addTeacher(TeacherModel teacherModel);
    ResponseEntity<String> updateTeacherDetails(Long staffId,TeacherModel teacherModel);
    ResponseEntity<String> deleteTeacherDetails(Long staffId);
}
