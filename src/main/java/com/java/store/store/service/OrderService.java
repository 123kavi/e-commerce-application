package com.java.store.store.service;

import java.util.List;

import com.java.store.store.domain.Order;
import com.java.store.store.domain.Payment;
import com.java.store.store.domain.Shipping;
import com.java.store.store.domain.ShoppingCart;
import com.java.store.store.domain.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
}
