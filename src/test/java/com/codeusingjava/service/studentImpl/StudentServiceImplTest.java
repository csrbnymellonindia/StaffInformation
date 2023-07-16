package com.codeusingjava.service.studentImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Optional;

import com.codeusingjava.controller.TeacherController;
import com.codeusingjava.model.StudentModel;
import com.codeusingjava.repository.StudentRepository;


public class StudentServiceImplTest {
       
	@Mock
	StudentRepository studentRepository = mock(StudentRepository.class);
	
	@InjectMocks
	StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentRepository);
	
	StudentModel studentModel = new StudentModel();
	
	@BeforeEach
	void setup() {
		studentModel.setStudentIdentifier(123);
		studentModel.setStudentFirstName("abc");
		studentModel.setStudentLastName("xyz");
		studentModel.setAdmittedGrade("A");
		studentModel.setStudentContactNumber("1234567895");
		studentModel.setStudentBirthDayNumber(12);
		studentModel.setStudentBirthMonthNumber(1);
		studentModel.setStudentBirthYear("2002");
		studentModel.setStudentGender("Female");
	}
	
	@Test
	void testfindAllStudents() {
		Mockito.when(studentRepository.findAll()).thenReturn(new ArrayList<>());
		studentServiceImpl.findAllStudents();
	}
	
	@Test
	void testfindStudentById() {
		Mockito.when(studentRepository.findById((long)123)).thenReturn(null);
        studentServiceImpl.findStudentById((long)123);
	}
	
	@Test
	void testupdateStudent() {
		Optional<StudentModel> optionalStudent = Optional.of(studentModel);
		Mockito.when(studentRepository.findById((long)123)).thenReturn(optionalStudent);
		studentServiceImpl.updateStudent(123, studentModel);
	}
	
	@Test
	void testdeleteStudent() {
		doNothing().when(studentRepository).deleteById((long)123);
		studentServiceImpl.deleteStudent(123);
	}
	
	@Test
	void testaddStudent() {
		studentServiceImpl.addStudent(studentModel);
	}
	
}
