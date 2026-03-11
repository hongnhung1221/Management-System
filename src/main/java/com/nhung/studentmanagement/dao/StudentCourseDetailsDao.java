package com.nhung.studentmanagement.dao;

import java.util.List;

import com.nhung.studentmanagement.entity.StudentCourseDetails;

public interface StudentCourseDetailsDao {
	
	public List<StudentCourseDetails> findByStudentId(int id);
	
	public StudentCourseDetails findByStudentAndCourseId(int studentId, int courseId);
	
	public void deleteByStudentId(int id);
	
	public void deleteByStudentAndCourseId(int studentId, int courseId);
	
	public void save(StudentCourseDetails studentCourseDetails);
}
