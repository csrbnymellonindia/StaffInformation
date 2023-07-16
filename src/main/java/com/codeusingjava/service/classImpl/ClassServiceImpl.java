package com.codeusingjava.service.classImpl;

import com.codeusingjava.model.ClassModel;
import com.codeusingjava.repository.ClassRepository;
import com.codeusingjava.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public List<ClassModel> findAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Optional<ClassModel> findClassById(long classIdentifier) {
        return classRepository.findById((long) classIdentifier);
    }

    @Override
    public ResponseEntity<String> addClass(ClassModel classModel) {
        classRepository.save(classModel);
        return ResponseEntity.ok().body("Class added successfully!");
    }

    @Override
    public ResponseEntity<String> updateClass(long classIdentifier, ClassModel classModel) {
        Optional<ClassModel> optionalClass = classRepository.findById(classIdentifier);
        if (optionalClass.isPresent()) {
            ClassModel existingClass = optionalClass.get();
            existingClass.setGradeText(classModel.getGradeText());
            existingClass.setDivisionText(classModel.getDivisionText());
            existingClass.setStaffIdentifier(classModel.getStaffIdentifier());
            classRepository.save(existingClass);
            return ResponseEntity.ok().body("Class updated successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid class identifier");
        }
    }

    @Override
    public ResponseEntity<String> deleteClass(long classIdentifier) {
        classRepository.deleteById(classIdentifier);
        return ResponseEntity.ok().body("Class deleted successfully!");
    }
}
