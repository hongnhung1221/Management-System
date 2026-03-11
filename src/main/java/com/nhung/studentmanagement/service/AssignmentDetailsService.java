package com.nhung.studentmanagement.service;

import com.nhung.studentmanagement.entity.AssignmentDetails;

public interface AssignmentDetailsService {
	
	public AssignmentDetails findByAssignmentAndStudentCourseDetailsId(int assignmentId, int studentCourseDetailsId);
	
	public void save(AssignmentDetails studentCourseAssignmentDetails);
}
