package com.coseller.repository;

import org.springframework.data.repository.CrudRepository;

import com.coseller.domain.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long>{

}
