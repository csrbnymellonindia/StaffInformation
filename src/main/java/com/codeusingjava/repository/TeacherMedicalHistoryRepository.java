package com.codeusingjava.repository;

import com.codeusingjava.model.TeacherMedicalHistoryEntity;
import com.codeusingjava.model.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMedicalHistoryRepository extends JpaRepository<TeacherMedicalHistoryEntity, Long> {
}
