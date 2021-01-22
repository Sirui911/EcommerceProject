package com.coseller.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coseller.domain.CartItem;
import com.coseller.domain.Product;
import com.coseller.domain.ProductToCartItem;
import com.coseller.domain.ShoppingCart;
import com.coseller.domain.User;
import com.coseller.repository.CartItemRepository;
import com.coseller.repository.ProductToCartItemRepository;
import com.coseller.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductToCartItemRepository productToCartItemRepository;
	
    
	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart){
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}
	
	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getProduct().getSellPrice()).multiply(new BigDecimal (cartItem.getQty()));
		//java9不能使用setScale(int, int)，要用setScale(2, RoundingMode.HALF_EVEN)
		bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
		cartItem.setSubTotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		return cartItem;
		
	}
	
	@Override
	public CartItem addProductToCartItem(Product product, User user, int qty) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartItem : cartItemList) {
			if (product.getId() == cartItem.getProduct().getId()) {
				cartItem.setQty(cartItem.getQty() + qty);
				cartItem.setSubTotal(new BigDecimal(product.getSellPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(cartItem);
			}
		}
		
		//如果之前不在里面
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setProduct(product);
		
		cartItem.setQty(qty);
		cartItem.setSubTotal(new BigDecimal(product.getSellPrice()).multiply(new BigDecimal(qty)));
		cartItem = cartItemRepository.save(cartItem);
		
		ProductToCartItem productToCartItem = new ProductToCartItem();
		productToCartItem.setProduct(product);
		productToCartItem.setCartItem(cartItem);
		productToCartItemRepository.save(productToCartItem);
		
		return cartItem;
	}
	
	@Override
	public CartItem findById(Long cartItemId) {
		return cartItemRepository.findById(cartItemId).get();
	}
	
	@Override
	public void removeCartItem(CartItem cartItem) {
		productToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}
}

