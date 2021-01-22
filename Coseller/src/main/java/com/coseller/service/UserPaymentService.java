package com.coseller.service;

import com.coseller.domain.UserPayment;

public interface UserPaymentService {
	
    UserPayment findById(Long id);
    
    void removeById(Long id);
}
