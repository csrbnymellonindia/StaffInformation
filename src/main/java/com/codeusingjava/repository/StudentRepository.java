package com.codeusingjava.repository;

import com.codeusingjava.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {

}