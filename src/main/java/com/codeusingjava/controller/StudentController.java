package com.codeusingjava.controller;

import com.codeusingjava.model.StudentModel;
import com.codeusingjava.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/addStud")
    public ResponseEntity<String> addStudent(@RequestBody StudentModel student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/updateStud/{studentIdentifier}")
    public ResponseEntity<String> updateStudent(@PathVariable int studentIdentifier, @RequestBody StudentModel student) {
        return studentService.updateStudent(studentIdentifier, student);
    }

    @DeleteMapping("/deleteStud/{studentIdentifier}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentIdentifier) {
        return studentService.deleteStudent(studentIdentifier);
    }

    @GetMapping("/getAll")
    public List<StudentModel> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/getStud/{studentIdentifier}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable int studentIdentifier) {
        Optional<StudentModel> student = studentService.findStudentById(studentIdentifier);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
