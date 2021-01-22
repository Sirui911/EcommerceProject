package com.coseller.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.coseller.domain.CartItem;
import com.coseller.domain.ProductToCartItem;

@Transactional
public interface ProductToCartItemRepository extends CrudRepository<ProductToCartItem, Long>{
    void deleteByCartItem(CartItem cartItem);
}
