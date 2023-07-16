package com.codeusingjava.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import com.codeusingjava.service.AuditLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.codeusingjava.model.StudentModel;
import com.codeusingjava.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

class StudentControllerTest {
	
	@Mock
	StudentService studentService = mock(StudentService.class);
	AuditLogService auditLogService = mock(AuditLogService.class);
	
	@Autowired
	MockMvc mockMvc;

	private StudentModel studentModel = new StudentModel();
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	
	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new StudentController(studentService),new AuditLogController(auditLogService)).build();
        studentModel.setAdditionalDetails("abc");
        studentModel.setStudentIdentifier(123);
	}
	
	@Test
	void testaddStudent() throws Exception {
		ResponseEntity<String> res = null ;
		Mockito.when(studentService.addStudent(studentModel)).thenReturn(res);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/students/addStud").content(this.mapper.writeValueAsBytes(studentModel));
		mockMvc.perform(builder);
	}
	
	@Test
	void testupdateStudent() throws Exception {
	ResponseEntity<String> res = null;
	Mockito.when(studentService.updateStudent(123, studentModel)).thenReturn(res);
	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/students/updateStud/123").content(this.mapper.writeValueAsBytes(studentModel));
	mockMvc.perform(builder);
	}
	
	@Test
	void testdeleteStudent() throws Exception {
		 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/students/deleteStud/123");
		   mockMvc.perform(builder);
	}
	
	
	
	@Test
	void testgetAllStudents() throws Exception {
		Mockito.when(studentService.findAllStudents()).thenReturn(new ArrayList<>());
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/students/getAll");
		mockMvc.perform(builder);
	}
	
	@Test
	void testgetStudentById() throws Exception {
		Mockito.when(studentService.findAllStudents()).thenReturn(new ArrayList<>());
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/students/getStud/123");
		mockMvc.perform(builder);
	}

}
