package com.codeusingjava.controller;


import com.codeusingjava.model.TeacherFamilyFinancialInformation;
import com.codeusingjava.service.TeacherFamilyFinancialInformationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherFamilyFinancialInformationController {

    private final TeacherFamilyFinancialInformationService teacherFamilyFinancialInformationService;

    public TeacherFamilyFinancialInformationController(TeacherFamilyFinancialInformationService teacherFamilyFinancialInformationService) {
        this.teacherFamilyFinancialInformationService = teacherFamilyFinancialInformationService;
    }

    @RequestMapping(value="/teacherFamilyFinancialDetails",method= RequestMethod.GET)
    public List<TeacherFamilyFinancialInformation> findAllTeacherFamilyFinancialInformation(){
        return teacherFamilyFinancialInformationService.findAllTeacherFamilyFinancialInformation();
    }

    @RequestMapping(value="/teacherFamilyFinancialDetails/{staffId}",method= RequestMethod.GET)
    public Optional<TeacherFamilyFinancialInformation> findTeacherFamilyFinancialInformationById(@PathVariable Long staffId){
        return teacherFamilyFinancialInformationService.findTeacherFamilyFinancialInformationById(staffId);
    }

    @RequestMapping(value="/addTeacherFamilyFinancialDetails",method=RequestMethod.POST)
    public void addTeacherFamilyFinancialInformation(@RequestBody TeacherFamilyFinancialInformation teacherFamilyFinancialInformation){
        teacherFamilyFinancialInformationService.addTeacherFamilyFinancialInformation(teacherFamilyFinancialInformation);
    }

    @RequestMapping(value="/updateTeacherFamilyFinancialDetails/{staffId}",method=RequestMethod.PUT)
    public void updateTeacherFamilyFinancialInformation(@PathVariable Long staffId,@RequestBody TeacherFamilyFinancialInformation teacherFamilyFinancialInformation){
        teacherFamilyFinancialInformationService.updateTeacherFamilyFinancialInformation(staffId,teacherFamilyFinancialInformation);
    }


    @RequestMapping(value="/deleteTeacherFamilyFinancialDetails/{staffId}",method =RequestMethod.DELETE)
    public void updateTeacherFamilyFinancialInformation(@PathVariable Long staffId){
        teacherFamilyFinancialInformationService.deleteTeacherFamilyFinancialInformation(staffId);
    }
}
