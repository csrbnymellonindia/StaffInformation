package com.codeusingjava.service.teachermedicalhistoryimpl;


import com.codeusingjava.model.TeacherMedicalHistoryEntity;
import com.codeusingjava.model.TeacherModel;
import com.codeusingjava.repository.TeacherMedicalHistoryRepository;
import com.codeusingjava.service.TeacherMedicalHistoryService;
import com.codeusingjava.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherMedicalHistoryServiceImpl implements TeacherMedicalHistoryService {
    private final TeacherMedicalHistoryRepository teacherMedicalHistoryRepository;
    private final TeacherService teacherService;


    public TeacherMedicalHistoryServiceImpl(TeacherMedicalHistoryRepository teacherMedicalHistoryRepository, TeacherService teacherService) {
        this.teacherMedicalHistoryRepository = teacherMedicalHistoryRepository;

        this.teacherService = teacherService;
    }

    public boolean validate(TeacherMedicalHistoryEntity teacherMedicalHistoryEntity) {
        System.out.println("In validation");
        Optional< TeacherModel> list=teacherService.findById(teacherMedicalHistoryEntity.getStaffId());
        if(list.isEmpty()) {
//            System.out.println("list empty");
            return false;
        }
        if (teacherMedicalHistoryEntity.getBloodGroup() == null || teacherMedicalHistoryEntity.getBloodGroup().length() > 5) {
            return false;
        }
        if(String.valueOf(teacherMedicalHistoryEntity.getHeight()).length()==0){
            return false;
        }
        if(String.valueOf(teacherMedicalHistoryEntity.getWeight()).length()==0){
            return false;
        }
        if(teacherMedicalHistoryEntity.isDifferentlyAbled()){
            if(teacherMedicalHistoryEntity.getDisability()==null || teacherMedicalHistoryEntity.getDisability().length() > 255){
                return false;
            }
        }

        System.out.println("Validated");
        return true;
    }
    @Override
    public List<TeacherMedicalHistoryEntity> findAllTeachersMedicalHistory() {
        return teacherMedicalHistoryRepository.findAll();
    }

    @Override
    public Optional<TeacherMedicalHistoryEntity> findTeacherMedicalHistoryById(Long staffId){
        return teacherMedicalHistoryRepository.findById(staffId);
    }

    @Override
    public ResponseEntity<String> addTeacherMedicalHistory(TeacherMedicalHistoryEntity teacherMedicalHistoryEntity){
        if (validate(teacherMedicalHistoryEntity)) {
            teacherMedicalHistoryRepository.save(teacherMedicalHistoryEntity);

            return ResponseEntity.ok().body("Teacher Medical Details added successfully!");
        }
        else {
            return ResponseEntity.badRequest().body("Teacher Medical details are not valid");
        }
    }

    @Override
    public ResponseEntity<String> updateTeacherMedicalHistory(Long staffId,TeacherMedicalHistoryEntity teacherMedicalHistoryEntity){
        if (validate(teacherMedicalHistoryEntity)) {
            Optional<TeacherMedicalHistoryEntity> optionalTeacher = teacherMedicalHistoryRepository.findById(staffId);
            if (optionalTeacher.isPresent()) {
//                System.out.println("yes");
                TeacherMedicalHistoryEntity teacherMedicalHistory = optionalTeacher.get();
                teacherMedicalHistory.setBloodGroup(teacherMedicalHistoryEntity.getBloodGroup());
                teacherMedicalHistory.setHeight(teacherMedicalHistoryEntity.getHeight());
                teacherMedicalHistory.setWeight(teacherMedicalHistoryEntity.getWeight());
                teacherMedicalHistory.setDifferentlyAbled(teacherMedicalHistoryEntity.isDifferentlyAbled());
                teacherMedicalHistory.setDisability(teacherMedicalHistoryEntity.getDisability());

                // Update other properties as needed
                teacherMedicalHistoryRepository.save(teacherMedicalHistory);

            }
        }
        else {
            return ResponseEntity.badRequest().body("Teacher medical history details are not valid");
        }
        return ResponseEntity.ok().body("Teacher medical history details updated successfully!");
    }

    @Override
    public ResponseEntity<String> deleteTeacherMedicalHistory(Long staffId){
        teacherMedicalHistoryRepository.deleteById(staffId);
        return ResponseEntity.ok().body("Teacher deleted successfully!");
    }
}
