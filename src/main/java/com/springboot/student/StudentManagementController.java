package com.springboot.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/students")
public class StudentManagementController {

	private List<Student> STUDENTS = Arrays.asList(new Student(1, "Vamsi"), new Student(2, "Sunny"));

	@GetMapping("all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ADMIN_TRAINEE')")
	public List<Student> getStudents() {
		return STUDENTS;
	}
	@GetMapping("{studentId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ADMIN_TRAINEE')")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream()
				.filter(student -> studentId.equals(student.getId()))
				.findFirst()
				.orElseThrow(() ->new IllegalArgumentException("no such student"));
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void insertStudent(@RequestBody Student student) {
		System.out.println("adding student");
	}
	
	@PutMapping("{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable("studentId") @RequestBody Student student) {
		System.out.println("updating student");
	}
	
	@DeleteMapping("{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println("deleting student");
	}
}
