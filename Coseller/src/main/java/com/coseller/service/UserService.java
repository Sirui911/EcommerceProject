package com.coseller.service;

import java.util.Set;

import com.coseller.domain.User;
import com.coseller.domain.security.PasswordResetToken;
import com.coseller.domain.security.UserRole;

public interface UserService {
    
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	//to check if there is exsited username and email
	User findByUsername (String username);
	
	User findByEmail (String email);
	
	User createUser(User user, Set<UserRole> userRoles)throws Exception;
	
	User save(User user);
}
