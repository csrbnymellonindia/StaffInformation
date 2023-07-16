package com.codeusingjava.service.teacherImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.codeusingjava.repository.TeacherMedicalHistoryRepository;
import com.codeusingjava.repository.TeacherRepository;
import com.codeusingjava.service.TeacherService;
import com.codeusingjava.model.TeacherModel;

import java.util.ArrayList;
import java.util.Optional;

class TeacherServiceImplTest {

	@Mock
	private TeacherRepository teacherRepository = mock(TeacherRepository.class);
	
	@Mock
    private  TeacherMedicalHistoryRepository teacherMedicalHistoryRepository = mock(TeacherMedicalHistoryRepository.class);

	
	
	@InjectMocks
	TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl(teacherRepository, teacherMedicalHistoryRepository);
	
	TeacherModel teacherModel = new TeacherModel();
	
	@BeforeEach
	void setup() {
		teacherModel.setStaffName("abc");
		teacherModel.setAddress("camp");
		teacherModel.setEmailId("abc@gmail.com");
	}
	
	@Test
	void testfindAllTeachers() {
		Mockito.when(teacherRepository.findAll()).thenReturn(new ArrayList<>());
		teacherServiceImpl.findAllTeachers();
	}
	
	@Test
	void testfindById() {
		Optional<TeacherModel> optional = Optional.of(teacherModel);
		Mockito.when(teacherRepository.findById((long)123)).thenReturn(optional);
		teacherServiceImpl.findById((long)123);
	}

	@Test
	void testaddTeacher() {
		Mockito.when(teacherRepository.save(teacherModel)).thenReturn(teacherModel);
		teacherServiceImpl.addTeacher(teacherModel);
	}
	
	@Test
	void testdeleteTeacherDetails() {
		doNothing().when(teacherRepository).delete(teacherModel);
		teacherServiceImpl.deleteTeacherDetails((long)123);
	}
	
	@Test
	void testvalidate() {
		teacherServiceImpl.validate(teacherModel);
	}
	
	@Test
	void testupdateTeacherDetails() {
		Optional<TeacherModel> optional = Optional.of(teacherModel);
		Mockito.when(teacherRepository.findById((long)123)).thenReturn(optional);
		teacherServiceImpl.updateTeacherDetails((long)123, teacherModel);
	}
}
