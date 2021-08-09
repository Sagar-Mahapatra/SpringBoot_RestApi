package com.springRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springRest.entities.Course;
import com.springRest.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

//get all courses	
	@GetMapping("/springBootRest/courses")
	public List<Course> getAllCourses() {
		List<Course> courses = courseService.getCourses();
		System.out.println(courses);
		return courses;

	}

//get course by Id	
	@GetMapping("/springBootRest/courses/{id}")
	public List<Course> getCourseById(@PathVariable String id) {
		List<Course> course = courseService.getCourseById(Long.parseLong(id));
		System.out.println(course);

		return course;

	}

	// get course by Name
	@GetMapping("/springBootRest/course/{name}")
	public ResponseEntity<Course> getCourseByName(@PathVariable String name) {
		Course course = courseService.getCourseByName(name);
		System.out.println(course);
		if (course != null) {
			return new ResponseEntity<>(course, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(course, HttpStatus.NOT_FOUND);
		}

	}

// Add course
	@PostMapping("/springBootRest/courses")
	public ResponseEntity<String> addCourse(@RequestBody Course course) {
		boolean AddedCourse = courseService.addCourse(course);
		System.out.println("Course added: " + AddedCourse);
		if (AddedCourse) {
			return new ResponseEntity<>(course + " Course Added", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(course + " Course not Added", HttpStatus.BAD_REQUEST);
		}

	}

}
