package com.coseller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coseller.domain.UserShipping;
import com.coseller.repository.UserShippingRepository;
import com.coseller.service.UserShippingService;

@Service
public class UserShippingServiceImpl implements UserShippingService{
	@Autowired
	UserShippingRepository userShippingRepository;
	
	
    @Override
    public UserShipping findById(Long id) {
    	return userShippingRepository.findById(id).get();
    }
    
    @Override
    public void removeById(Long id) {
    	userShippingRepository.deleteById(id);
    }
}
