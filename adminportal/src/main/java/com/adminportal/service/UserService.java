package com.adminportal.service;

import java.util.Set;

import com.adminportal.domain.User;
import com.adminportal.domain.security.PasswordResetToken;
import com.adminportal.domain.security.UserRole;



public interface UserService {
	
	//to check if there is exsited username and email
	
	User createUser(User user, Set<UserRole> userRoles)throws Exception;
	
	User save(User user);
	
}
