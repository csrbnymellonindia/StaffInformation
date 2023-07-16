package com.codeusingjava.controller;

import com.codeusingjava.model.TeacherMedicalHistoryEntity;
import com.codeusingjava.model.TeacherModel;
import com.codeusingjava.service.TeacherMedicalHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@RestController
public class TeacherMedicalDetailsController {
    private final TeacherMedicalHistoryService teacherMedicalHistoryService;

    public TeacherMedicalDetailsController(TeacherMedicalHistoryService teacherMedicalHistoryService) {
        this.teacherMedicalHistoryService = teacherMedicalHistoryService;
    }

    @RequestMapping(value = "/teacherMedicalDetails", method = RequestMethod.GET)
    public List<TeacherMedicalHistoryEntity> findAllTeachersMedicalHistory(){
        return teacherMedicalHistoryService.findAllTeachersMedicalHistory();
    }

    @RequestMapping(value = "/teacherMedicalDetails/{staffId}", method = RequestMethod.GET)
    public Optional<TeacherMedicalHistoryEntity> findTeacherMedicalHistoryById(@PathVariable Long staffId){
        return teacherMedicalHistoryService.findTeacherMedicalHistoryById(staffId);
    }

    @RequestMapping(value = "/addTeacherMedicalHistory", method = RequestMethod.POST)
    public void  addTeacherMedicalHistory(@RequestBody TeacherMedicalHistoryEntity teacherMedicalHistoryEntity){
        teacherMedicalHistoryService.addTeacherMedicalHistory(teacherMedicalHistoryEntity);
    }

    @RequestMapping(value="/updateTeacherMedicalHistory/{staffId}",method=RequestMethod.PUT)
    public void updateTeacherMedicalHistory(@PathVariable Long staffId,@RequestBody TeacherMedicalHistoryEntity teacherMedicalHistoryEntity){
        teacherMedicalHistoryService.updateTeacherMedicalHistory(staffId,teacherMedicalHistoryEntity);
    }

    @RequestMapping(value="/deleteTeacherMedicalHistory/{staffId}",method=RequestMethod.DELETE)
    public void deleteTeacherMedicalHistory(@PathVariable Long staffId){
        teacherMedicalHistoryService.deleteTeacherMedicalHistory(staffId);
    }
}
