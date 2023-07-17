package com.codeusingjava.repository;

import com.codeusingjava.model.TeacherFamilyFinancialInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherFamilyFinancialInformationRepository extends JpaRepository<TeacherFamilyFinancialInformation,Long> {
}
