package com.springboot.student;

public class Student {

	int id;
	String nameOfStudent;
	
	public Student(int id, String nameOfStudent) {
		this.id =id;
		this.nameOfStudent = nameOfStudent;
	}
	
		
	public int getId() {
		return id;
	}

	public String getNameOfStudent() {
		return nameOfStudent;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", nameOfStudent=" + nameOfStudent + "]";
	}

}
