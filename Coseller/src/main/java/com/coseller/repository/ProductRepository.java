package com.coseller.repository;

import org.springframework.data.repository.CrudRepository;

import com.coseller.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
