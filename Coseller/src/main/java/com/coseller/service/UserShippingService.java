package com.coseller.service;

import com.coseller.domain.UserShipping;

public interface UserShippingService {
    UserShipping findById(Long id);
    
    void removeById(Long id);
}
