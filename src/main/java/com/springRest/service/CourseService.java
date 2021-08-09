package com.springRest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springRest.entities.Course;

@Service
public class CourseService {

	List<Course> list = new ArrayList<Course>();

	public CourseService() {
		list.add(new Course(101, "Java", "Java is a platform independent Programming Language"));
		list.add(new Course(102, "Python", "Python is mainly used for ML"));
		list.add(new Course(103, "Angular", "Angular is mainly used for developing SPA"));

	}

	public List<Course> getCourses() {

		return list;

	}

	public List<Course> getCourseById(long id) {

		List<Course> collect = list.stream().filter(course -> course.getCourseId() == id).collect(Collectors.toList());

		return collect;
	}

	public boolean addCourse(Course course) {
		
		boolean add = list.add(course);
		
		return add;
	}

	public Course getCourseByName(String name) {
		Course course = null;
		for (Course c : list) {
			if (c.getCourseName().equalsIgnoreCase(name)) {
				course = c;
			}

		}
		return course;

	}

}
