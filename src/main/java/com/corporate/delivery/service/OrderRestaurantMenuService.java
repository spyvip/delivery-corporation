package com.corporate.delivery.service;

import java.util.List;

import com.corporate.delivery.model.OrderRestaurantMenu;

public interface OrderRestaurantMenuService {

	public List<OrderRestaurantMenu> getOrderRestaurantMenu(Integer userId);
	
	public void insert(OrderRestaurantMenu orderRestaurantMenu) ;
	
	public void updateOrderRestaurantMenu(OrderRestaurantMenu orderRestaurantMenu);
	
	public void deleteOrderRestaurantMenu(OrderRestaurantMenu orderRestaurantMenu);
	
}