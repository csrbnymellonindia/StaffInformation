package com.codeusingjava.controller;

import com.codeusingjava.model.TeacherModel;
import com.codeusingjava.service.AuditLogService;
import com.codeusingjava.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {
    private final TeacherService teacherService;
    private final AuditLogService auditLogService;

    public TeacherController(TeacherService teacherService,AuditLogService auditLogService) {
        this.teacherService = teacherService;
        this.auditLogService = auditLogService;
    }

    @RequestMapping(value = "/teacherDetails", method = RequestMethod.GET)
    
    public List<TeacherModel> findAllTeachers(){
        return teacherService.findAllTeachers();
    }

    @RequestMapping(value = "/teacherDetails/{staffId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public Optional<TeacherModel> findById(@PathVariable Long staffId){
        return teacherService.findById(staffId);
    }

    @RequestMapping(value = "/createTeacher", method = RequestMethod.POST)
    public void addTeacher(@RequestBody TeacherModel teacherModel){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSSSS]");
        auditLogService.createAuditLogEntry((int) teacherModel.getStaffId(), String.valueOf(currentDateTime.format(formatter)), "Insertion");
        System.out.println("In controller");
        teacherService.addTeacher(teacherModel);
    }
    @RequestMapping(value = "/updateTeacher/{staffId}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('admin')")
    public void updateTeacher(@PathVariable Long staffId,@RequestBody TeacherModel teacherModel){

        teacherService.updateTeacherDetails(staffId,teacherModel);
    }

    @RequestMapping(value = "/deleteTeacher/{staffId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('admin')")
    public void deleteTeacherDetails(@PathVariable Long staffId){
        teacherService.deleteTeacherDetails(staffId);

    }


}
