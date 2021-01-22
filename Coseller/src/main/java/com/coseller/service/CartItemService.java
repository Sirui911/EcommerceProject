package com.coseller.service;

import java.util.List;

import com.coseller.domain.CartItem;
import com.coseller.domain.Product;
import com.coseller.domain.ShoppingCart;
import com.coseller.domain.User;

public interface CartItemService {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addProductToCartItem(Product product, User user, int qty);
	
	CartItem findById(Long cartItemId);
	
	void removeCartItem(CartItem cartItem);
}
