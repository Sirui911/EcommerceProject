package com.coseller.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coseller.domain.ShoppingCart;
import com.coseller.domain.User;
import com.coseller.domain.UserBilling;
import com.coseller.domain.UserPayment;
import com.coseller.domain.UserShipping;
import com.coseller.domain.security.PasswordResetToken;
import com.coseller.domain.security.UserRole;
import com.coseller.repository.PasswordResetTokenRepository;
import com.coseller.repository.RoleRepository;
import com.coseller.repository.UserPaymentRepository;
import com.coseller.repository.UserRepository;
import com.coseller.repository.UserShippingRepository;
import com.coseller.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}
    
	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}
    
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
    
	@Override
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}
    
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception{
    	User localUser = userRepository.findByUsername(user.getUsername());
    	
        if (localUser != null) {
       	    LOG.info("user {} is already exsits and nothing will be done!", user.getUsername());
        }else {
        	for (UserRole ur : userRoles) {
        		roleRepository.save(ur.getRole());
        	}
        	
        	user.getUserRoles().addAll(userRoles);
        	
        	ShoppingCart shoppingCart = new ShoppingCart();
        	shoppingCart.setUser(user);
        	user.setShoppingcart(shoppingCart);
        	
        	user.setUserShippingList(new ArrayList<UserShipping>());
        	user.setUserPaymentList(new ArrayList<UserPayment>());
        	
        	localUser = userRepository.save(user);
        }
        return localUser;
    }
    
    @Override
	public User save(User user) {
		return userRepository.save(user);
	}
    
    @Override
    public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
    	userPayment.setUser(user);
    	userPayment.setUserBilling(userBilling);
    	userPayment.setDefaultPayment(true);
    	userBilling.setUserPayment(userPayment);
    	user.getUserPaymentList().add(userPayment);
    	save(user);
    }
    
    @Override
    public void updateUserShipping(UserShipping userShipping, User user) {
    	//为什么这个里面不需要用userShipping 去save改变后的userShipping
    	userShipping.setUser(user);
    	userShipping.setUserShippingDefault(true);
    	user.getUserShippingList().add(userShipping);
    	save(user);
    }
    
    @Override
    public void setDefaultPayment(Long userPaymentId, User user) {
    	List<UserPayment> userPaymentList = (List<UserPayment>)userPaymentRepository.findAll();
    	for (UserPayment userPayment : userPaymentList) {
    		if (userPayment.getId() == userPaymentId) {
    			userPayment.setDefaultPayment(true);
    			userPaymentRepository.save(userPayment);
    		}else {
    			userPayment.setDefaultPayment(false);
    			userPaymentRepository.save(userPayment);
    		}
    	}
    }
    
    @Override
    public void setDefaultShippingAddress(Long userShippingId, User user) {
    	List<UserShipping> userShippingList = (List<UserShipping>)userShippingRepository.findAll();
    	for (UserShipping userShipping : userShippingList) {
    		if (userShipping.getId() == userShippingId) {
    		    userShipping.setUserShippingDefault(true);
    		    userShippingRepository.save(userShipping);
    		}else {
    			userShipping.setUserShippingDefault(false);
        		userShippingRepository.save(userShipping);
    		}
    	}
    }
}
 