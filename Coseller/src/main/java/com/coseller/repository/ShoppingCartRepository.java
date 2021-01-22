package com.coseller.repository;

import org.springframework.data.repository.CrudRepository;

import com.coseller.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{

}
