package com.codeusingjava.service;

import com.codeusingjava.model.StudentModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentModel> findAllStudents();

    Optional<StudentModel> findStudentById(long studentIdentifier);

    ResponseEntity<String> addStudent(StudentModel student);

    ResponseEntity<String> updateStudent(int studentIdentifier, StudentModel student);

    ResponseEntity<String> deleteStudent(int studentIdentifier);

}
