package com.nhung.studentmanagement.service;

import com.nhung.studentmanagement.entity.GradeDetails;

public interface GradeDetailsService {
	
	public void save(GradeDetails gradeDetails);
	
	public GradeDetails findById(int id);
	
	public void deleteById(int id);
}
