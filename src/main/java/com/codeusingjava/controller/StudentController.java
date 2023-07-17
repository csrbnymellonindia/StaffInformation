package com.codeusingjava.controller;

import com.codeusingjava.model.AuditLogModel;
import com.codeusingjava.model.StudentModel;
import com.codeusingjava.model.UserDao;
import com.codeusingjava.service.AuditLogService;
import com.codeusingjava.service.JwtUserDetailsService;
import com.codeusingjava.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final AuditLogService auditLogService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    public StudentController(StudentService studentService, AuditLogService auditLogService) {
        this.studentService = studentService;
        this.auditLogService = auditLogService;
    }

    @PostMapping("/addStud")
    public ResponseEntity<String> addStudent(@RequestBody StudentModel student) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSSSS]");
        auditLogService.createAuditLogEntry((int) student.getStudentIdentifier(),
                String.valueOf(currentDateTime.format(formatter)), "Added Student");
        userDetailsService.save(new UserDao(student.getStudentFirstName(),String.valueOf(student.getPrimaryContactNumber()), "student"));

        return studentService.addStudent(student);
    }

    @PutMapping("/updateStud/{studentIdentifier}")
    public ResponseEntity<String> updateStudent(@PathVariable int studentIdentifier,
            @RequestBody StudentModel student) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSSSS]");
        auditLogService.createAuditLogEntry(studentIdentifier, String.valueOf(currentDateTime.format(formatter)),
                "Updated Student");
        return studentService.updateStudent(studentIdentifier, student);
    }

    @DeleteMapping("/deleteStud/{studentIdentifier}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentIdentifier) {
         LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSSSS]");
         auditLogService.createAuditLogEntry(studentIdentifier, String.valueOf(currentDateTime.format(formatter)),
                "Deleted Student");
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
