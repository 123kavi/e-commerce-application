package com.java.store.store.service;

import com.java.store.store.domain.Product;
import com.java.store.store.domain.CartItem;
import com.java.store.store.domain.ShoppingCart;
import com.java.store.store.domain.User;


public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	CartItem findCartItemById(Long cartItemId);
	
	CartItem addProductToShoppingCart(Product product, User user, int qty, String size);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);
	
}
