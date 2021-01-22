package com.coseller.service;

import java.util.Set;

import com.coseller.domain.User;
import com.coseller.domain.UserBilling;
import com.coseller.domain.UserPayment;
import com.coseller.domain.UserShipping;
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
	
	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	
	void updateUserShipping(UserShipping userShipping, User user);
	
	void setDefaultPayment(Long userPaymentId, User user);
	
	void setDefaultShippingAddress(Long userShippingID, User user);
}
