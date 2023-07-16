package com.codeusingjava.service;

import com.codeusingjava.model.ClassModel;
import com.codeusingjava.model.StudentModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClassService {

    List<ClassModel> findAllClasses();

    Optional<ClassModel> findClassById(long studentIdentifier);

    ResponseEntity<String> addClass(ClassModel Class);

    ResponseEntity<String> updateClass(long classIdentifier, ClassModel Class);

    ResponseEntity<String> deleteClass(long classIdentifier);

}
