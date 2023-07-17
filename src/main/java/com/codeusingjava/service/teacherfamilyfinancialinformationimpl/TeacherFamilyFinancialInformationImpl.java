package com.codeusingjava.service.teacherfamilyfinancialinformationimpl;


import com.codeusingjava.model.TeacherFamilyFinancialInformation;
import com.codeusingjava.model.TeacherModel;
import com.codeusingjava.repository.TeacherFamilyFinancialInformationRepository;
import com.codeusingjava.service.TeacherFamilyFinancialInformationService;
import com.codeusingjava.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherFamilyFinancialInformationImpl implements TeacherFamilyFinancialInformationService {


    private final TeacherFamilyFinancialInformationRepository teacherFamilyFinancialInformationRepository;
    private final TeacherService teacherService;

    public TeacherFamilyFinancialInformationImpl(TeacherFamilyFinancialInformationRepository teacherFamilyFinancialInformationRepository, TeacherService teacherService) {
        this.teacherFamilyFinancialInformationRepository = teacherFamilyFinancialInformationRepository;
        this.teacherService = teacherService;
    }
    public boolean validate(TeacherFamilyFinancialInformation teacherFamilyFinancialInformation){
        System.out.println("In validation");
        Optional<TeacherModel> list=teacherService.findById(teacherFamilyFinancialInformation.getStaffId());
        if(list.isEmpty()) {
//            System.out.println("list empty");
            return false;
        }
        if (teacherFamilyFinancialInformation.getBankName()==null || teacherFamilyFinancialInformation.getBankName().length() > 255) {
            return false;
        }
        if(String.valueOf(teacherFamilyFinancialInformation.getBankAccountNumber()).length()<=9 && String.valueOf(teacherFamilyFinancialInformation.getBankAccountNumber()).length()>=18){
            return false;
        }
        if(teacherFamilyFinancialInformation.getBankIFSCCode().length()!=11){
            return false;
        }
        return true;
    }
    @Override
    public List<TeacherFamilyFinancialInformation> findAllTeacherFamilyFinancialInformation() {
        return  teacherFamilyFinancialInformationRepository.findAll();
    }

    @Override
    public Optional<TeacherFamilyFinancialInformation> findTeacherFamilyFinancialInformationById(Long staffId) {
        return teacherFamilyFinancialInformationRepository.findById(staffId);
    }

    @Override
    public ResponseEntity<String> addTeacherFamilyFinancialInformation(TeacherFamilyFinancialInformation teacherFamilyFinancialInformation) {
        if(validate(teacherFamilyFinancialInformation)){
            teacherFamilyFinancialInformationRepository.save(teacherFamilyFinancialInformation);
            return ResponseEntity.ok().body("Teacher Family Financial Information added successfully");
        }
        else return ResponseEntity.badRequest().body("Teacher Family Financial Information invalid!");
    }

    @Override
    public ResponseEntity<String> updateTeacherFamilyFinancialInformation(Long staffId, TeacherFamilyFinancialInformation teacherFamilyFinancialInformation) {
        if (validate(teacherFamilyFinancialInformation)) {
            Optional<TeacherFamilyFinancialInformation> teacherFamilyFinancialInformation1 = teacherFamilyFinancialInformationRepository.findById(staffId);
            if (teacherFamilyFinancialInformation1.isPresent()) {
                TeacherFamilyFinancialInformation teacherFamilyFinancialInformation2 = teacherFamilyFinancialInformation1.get();
                teacherFamilyFinancialInformation2.setBankName(teacherFamilyFinancialInformation.getBankName());
                teacherFamilyFinancialInformation2.setBankAccountNumber(teacherFamilyFinancialInformation.getBankAccountNumber());
                teacherFamilyFinancialInformation2.setBankIFSCCode(teacherFamilyFinancialInformation.getBankIFSCCode());
                teacherFamilyFinancialInformationRepository.save(teacherFamilyFinancialInformation2);
            } else {
                return ResponseEntity.badRequest().body("Teacher family financial details are not valid");
            }


        }
        return ResponseEntity.ok().body("Teacher family financial details updated successfully!");
    }

    @Override
    public ResponseEntity<String> deleteTeacherFamilyFinancialInformation(Long staffId) {
        teacherFamilyFinancialInformationRepository.deleteById(staffId);
        return ResponseEntity.ok().body("Teacher family financial details deleted successfully!");
    }
}
