package com.codeusingjava.controller;

import static org.mockito.Mockito.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;

import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.codeusingjava.model.TeacherModel;
import com.codeusingjava.service.AuditLogService;
import com.codeusingjava.service.TeacherService;

import org.springframework.test.web.servlet.MockMvc;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

class TeacherControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	TeacherService teacherService = mock(TeacherService.class);

	private TeacherModel teacherModel = new TeacherModel();
	
	private final ObjectMapper mapper = new ObjectMapper();	
	
	@MockBean
	private final AuditLogService auditLogService = mock(AuditLogService.class) ;

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new TeacherController(teacherService, auditLogService)).build();
        teacherModel.setStaffId((long)123);
        teacherModel.setAddress("abc");
        teacherModel.setEmailId("xyz");
        teacherModel.setPrimaryContactNumber((long)12364656);
        teacherModel.setSecondaryContactNumber((long)79643);
        teacherModel.setStaffName("abc");
        teacherModel.setWhatsappNumber((long)12364656);
        
	}

	@Test
	void testfindAllTeachers() throws Exception {

		Mockito.when(teacherService.findAllTeachers()).thenReturn(new ArrayList<>());

		MockHttpServletRequestBuilder builder = get("/teacherDetails");

		mockMvc.perform(builder);

	}

	@Test
	void testfindById() throws Exception {

		Optional<TeacherModel> value = Optional.ofNullable(new TeacherModel());

		Mockito.when(teacherService.findById((long) 123)).thenReturn(value);

		MockHttpServletRequestBuilder builder = get("/teacherDetails/123");

		mockMvc.perform(builder);

	}

	@Test
	void test() throws Exception {
		ResponseEntity<String> res = null ;
		Mockito.when(teacherService.updateTeacherDetails((long)123, teacherModel)).thenReturn(res);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/updateTeacher/123").content(this.mapper.writeValueAsBytes(teacherModel));
		mockMvc.perform(builder);
 
	}
	
	@Test
	void testAddTeacher() throws Exception {
		ResponseEntity<String> res = null;
		Mockito.when(teacherService.addTeacher(teacherModel)).thenReturn(res);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/createTeacher").content(this.mapper.writeValueAsBytes(teacherModel));
		mockMvc.perform(builder);
	}
	
	@Test
	void testdeleteTeacherDetails() throws Exception {
	   MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/deleteTeacher/123");
	   mockMvc.perform(builder);
	}

}
