package com.codeusingjava.controller;

import com.codeusingjava.model.ClassModel;
import com.codeusingjava.service.AuditLogService;
import com.codeusingjava.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;
    private final AuditLogService auditLogService;

    public ClassController(ClassService classService,AuditLogService auditLogService) {
        this.classService = classService;
        this.auditLogService = auditLogService;
    }

    @PostMapping("/addClass")
    public ResponseEntity<String> addClass(@RequestBody ClassModel classModel) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSSSS]");
         auditLogService.createAuditLogEntry(classModel.getClassIdentifier(), String.valueOf(currentDateTime.format(formatter)), "Added Class");
       
        return classService.addClass(classModel);
    }

    @PutMapping("/updateClass/{classIdentifier}")
    public ResponseEntity<String> updateClass(@PathVariable int classIdentifier, @RequestBody ClassModel classModel) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSSSS]");
         auditLogService.createAuditLogEntry(classModel.getClassIdentifier(), String.valueOf(currentDateTime.format(formatter)), "Updated Class");
       
        return classService.updateClass(classIdentifier, classModel);
    }

    @DeleteMapping("/deleteClass/{classIdentifier}")
    public ResponseEntity<String> deleteClass(@PathVariable int classIdentifier) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSSSS]");
         auditLogService.createAuditLogEntry(classIdentifier, String.valueOf(currentDateTime.format(formatter)), "Deleted Class");
       
        return classService.deleteClass(classIdentifier);
    }

    @GetMapping("/getAll")
    public List<ClassModel> getAllClasses() {
        return classService.findAllClasses();
    }

    @GetMapping("/getClass/{classIdentifier}")
    public ResponseEntity<ClassModel> getClassById(@PathVariable int classIdentifier) {
        Optional<ClassModel> classModel = classService.findClassById(classIdentifier);
        return classModel.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
