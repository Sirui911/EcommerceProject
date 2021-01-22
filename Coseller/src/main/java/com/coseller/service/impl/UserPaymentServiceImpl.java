package com.coseller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coseller.domain.UserPayment;
import com.coseller.repository.UserPaymentRepository;
import com.coseller.service.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService{
	
	@Autowired
	UserPaymentRepository userPaymentRepository;
	
	
	@Override
    public UserPayment findById(Long id) {
    	return userPaymentRepository.findById(id).get();
    }
	
	@Override
	public void removeById(Long id) {
		userPaymentRepository.deleteById(id);
	}
}
