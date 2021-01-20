package com.coseller.service;

import java.util.List;

import com.coseller.domain.Product;

public interface ProductService {

	List<Product> findAll();
	
	Product findOne(Long id);
}
