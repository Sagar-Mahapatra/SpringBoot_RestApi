package com.springRest.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springRest.controller.CourseController;
import com.springRest.entities.Course;
import com.springRest.service.CourseService;

@WebMvcTest(value = CourseController.class)
public class CourseRestControllerTest {

	static List<Course> list = new ArrayList<>();

	static {
		list.add(new Course(100, "dummy", "dummy value"));
	}

	@MockBean
	private CourseService courseService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllCourses() throws Exception {
		when(courseService.getCourses()).thenReturn(list);
		int status = mockMvc.perform(MockMvcRequestBuilders.get("/springBootRest/courses")).andReturn().getResponse()
				.getStatus();
		assertEquals(200, status);
		System.out.println("testGetAllCourses() executed...");

	}

//checking for true condition	
	@Test
	public void testAddCourse01() throws Exception {
		when(courseService.addCourse(ArgumentMatchers.any())).thenReturn(true);

		Course course = new Course(106, "react", "react is javascript library to create SPA");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(course);

		int status = mockMvc.perform(MockMvcRequestBuilders.post("/springBootRest/courses")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andReturn().getResponse().getStatus();

		assertEquals(201, status);

		System.out.println("testAddCourse01() executed...");

	}

//checking for false condition	
	@Test
	public void testAddCourse02() throws Exception {
		when(courseService.addCourse(ArgumentMatchers.any())).thenReturn(false);

		Course course = new Course(106, "react", "react is javascript library to create SPA");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(course);

		int status = mockMvc.perform(MockMvcRequestBuilders.post("/springBootRest/courses")
				.contentType(MediaType.APPLICATION_JSON).content(json)).andReturn().getResponse().getStatus();

		assertEquals(400, status);

		System.out.println("testAddCourse02() executed...");

	}

//Testing getCourseByName01 method	
	@Test
	public void testGetCourseByName01() throws Exception {
		System.out.println("testGetCourseByName01() executed...");
		when(courseService.getCourseByName(ArgumentMatchers.anyString()))
				.thenReturn(new Course(100, "dummy", "dummy object"));
		int status = mockMvc.perform(MockMvcRequestBuilders.get("/springBootRest/course/java")).andReturn()
				.getResponse().getStatus();
		assertEquals(302, status);

	}

// Testing getCourseByName02 method
	@Test
	public void testGetCourseByName02() throws Exception {
		System.out.println("testGetCourseByName02() executed...");
		when(courseService.getCourseByName(ArgumentMatchers.anyString())).thenReturn(null);
		int status = mockMvc.perform(MockMvcRequestBuilders.get("/springBootRest/course/corejava")).andReturn()
				.getResponse().getStatus();
		assertEquals(404, status);

	}

//Testing getCourseById method	
	@Test
	public void testGetCourseById() throws Exception {
		System.out.println("testGetCourseById() executed...");
		when(courseService.getCourseById(ArgumentMatchers.anyInt())).thenReturn(list);
		int status = mockMvc.perform(MockMvcRequestBuilders.get("/springBootRest/courses/101")).andReturn()
				.getResponse().getStatus();
		assertEquals(200, status);

	}
}
