package com.corporate.delivery.service;

import java.util.List;

import com.corporate.delivery.model.order.OrderRestaurant;

public interface OrderRestaurantService {

	public List<OrderRestaurant> getOrderRestaurant(Integer orderId);
	
	public void insert(OrderRestaurant orderRestaurant) ;
	
	public void updateOrderRestaurant(OrderRestaurant orderRestaurant);
	
	public void deleteOrderRestaurant(Integer orderId);
}
