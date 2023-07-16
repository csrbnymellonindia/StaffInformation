package com.codeusingjava.controller;

import com.codeusingjava.model.TeacherModel;
import com.codeusingjava.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(value = "/teacherDetails", method = RequestMethod.GET)
    
    public List<TeacherModel> findAllTeachers(){
        return teacherService.findAllTeachers();
    }

    @RequestMapping(value = "/teacherDetails/{staffId}", method = RequestMethod.GET)
    public Optional<TeacherModel> findById(@PathVariable Long staffId){
        return teacherService.findById(staffId);
    }

    @RequestMapping(value = "/createTeacher", method = RequestMethod.POST)
    public void addTeacher(@RequestBody TeacherModel teacherModel){
        System.out.println("In controller");
        teacherService.addTeacher(teacherModel);
    }
    @RequestMapping(value = "/updateTeacher/{staffId}", method = RequestMethod.PUT)
    public void updateTeacher(@PathVariable Long staffId,@RequestBody TeacherModel teacherModel){

        teacherService.updateTeacherDetails(staffId,teacherModel);
    }

    @RequestMapping(value = "/deleteTeacher/{staffId}", method = RequestMethod.DELETE)
    public void deleteTeacherDetails(@PathVariable Long staffId){
        teacherService.deleteTeacherDetails(staffId);

    }


}
