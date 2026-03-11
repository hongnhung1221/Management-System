package com.nhung.studentmanagement.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nhung.studentmanagement.entity.Student;
import com.nhung.studentmanagement.entity.Teacher;
import com.nhung.studentmanagement.service.StudentService;
import com.nhung.studentmanagement.service.TeacherService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		
		
		String role = auth.getAuthorities().iterator().next().toString();
		
		if(role.equals("ROLE_STUDENT")) {
			String userName = auth.getName();
			Student theStudent = studentService.findByStudentName(userName);
			int userId = theStudent.getId(); 
			HttpSession session = request.getSession();
			session.setAttribute("user", theStudent);
			response.sendRedirect(request.getContextPath() + "/student/" + userId + "/courses");
			
		} else if(role.equals("ROLE_TEACHER")) {
			String userName = auth.getName();
			Teacher theTeacher = teacherService.findByTeacherName(userName);
			int userId = theTeacher.getId();
			HttpSession session = request.getSession();
			session.setAttribute("user", theTeacher);
			response.sendRedirect(request.getContextPath() + "/teacher/" + userId + "/courses");
		} else { 
			response.sendRedirect(request.getContextPath() + "/admin/adminPanel");
		}

	}

}

