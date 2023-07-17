package com.codeusingjava.service.teacherImpl;

import com.codeusingjava.model.TeacherMedicalHistoryEntity;
import com.codeusingjava.model.TeacherModel;
import com.codeusingjava.repository.TeacherFamilyFinancialInformationRepository;
import com.codeusingjava.repository.TeacherMedicalHistoryRepository;
import com.codeusingjava.repository.TeacherRepository;
import com.codeusingjava.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMedicalHistoryRepository teacherMedicalHistoryRepository;
    private final TeacherFamilyFinancialInformationRepository teacherFamilyFinancialInformationRepository;


    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMedicalHistoryRepository teacherMedicalHistoryRepository, TeacherFamilyFinancialInformationRepository teacherFamilyFinancialInformationRepository) {
        this.teacherRepository = teacherRepository;
        this.teacherMedicalHistoryRepository = teacherMedicalHistoryRepository;
        this.teacherFamilyFinancialInformationRepository = teacherFamilyFinancialInformationRepository;
    }

    public boolean validate(TeacherModel teacherModel) {
        System.out.println("In validation");

        if (teacherModel.getStaffName() == null || teacherModel.getStaffName().length() > 255) {
            System.out.println("In name");
            if(teacherModel.getStaffName() == null) System.out.println("Name null");
            return false;
        }
        if (String.valueOf(teacherModel.getPrimaryContactNumber()).length() > 10) {
            System.out.println("In pcn");
            return false;
        }
        if (String.valueOf(teacherModel.getSecondaryContactNumber()).length() > 10) {
            System.out.println("In scn");
            return false;
        }
        if (teacherModel.getAddress() == null || teacherModel.getAddress().length() > 255) {
            System.out.println("In address");
            return false;
        }
        if (teacherModel.getEmailId() == null || !teacherModel.getEmailId().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,6}$")) {
            System.out.println("In email");
            return false;
        }
        if (String.valueOf(teacherModel.getWhatsappNumber()).length() > 10) {
            System.out.println("In wn");
            return false;
        }
        System.out.println("Validated");
        return true;
    }

    @Override
    public List<TeacherModel> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<TeacherModel> findById(Long staffId) {
        return teacherRepository.findById(staffId);
    }


    @Override
    public ResponseEntity<String> addTeacher(TeacherModel teacherModel) {
        if (validate(teacherModel)) {

            teacherRepository.save(teacherModel);
            return ResponseEntity.ok().body("Teacher added successfully!");
        }
        else {
            return ResponseEntity.badRequest().body("Teacher details are not valid");
        }
    }



    @Override
    public ResponseEntity<String> updateTeacherDetails(Long staffId,TeacherModel teacherModel) {
        if (validate(teacherModel)) {
            Optional<TeacherModel> optionalTeacher = teacherRepository.findById(staffId);
            if (optionalTeacher.isPresent()) {
                TeacherModel teacherModel1 = optionalTeacher.get();
                teacherModel1.setStaffId(staffId);
                teacherModel1.setStaffName(teacherModel.getStaffName());
                teacherModel1.setPrimaryContactNumber(teacherModel.getPrimaryContactNumber());
                teacherModel1.setSecondaryContactNumber(teacherModel.getSecondaryContactNumber());
                teacherModel1.setWhatsappNumber(teacherModel.getWhatsappNumber());
                teacherModel1.setEmailId(teacherModel.getEmailId());
                teacherModel1.setAddress(teacherModel.getAddress());
                // Update other properties as needed
                teacherRepository.save(teacherModel1);

            }
        }
        else {
            return ResponseEntity.badRequest().body("Teacher details are not valid");
        }
        return ResponseEntity.ok().body("Teacher updated successfully!");
    }

    @Override
    public ResponseEntity<String> deleteTeacherDetails(Long staffId) {
        teacherRepository.deleteById(staffId);
        // teacherMedicalHistoryRepository.deleteById(staffId);
        // teacherFamilyFinancialInformationRepository.deleteById(staffId);
        return ResponseEntity.ok().body("Teacher deleted successfully!");
    }
}
