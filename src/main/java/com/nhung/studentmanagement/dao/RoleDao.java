package com.nhung.studentmanagement.dao;

import com.nhung.studentmanagement.entity.Role;

public interface RoleDao {
	
	public Role findRoleByName(String theRoleName);
}
