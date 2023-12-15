package com.example.service;

import java.util.List;

import com.example.model.Cart;
import com.example.model.Orders;

public interface OrderServiceInteface {
	
	/**
	 * This method adds new Order to user;
	 * @param orders
	 * @return Orders
	 */
	public Cart addOrdersFromCart(Integer userId);
	
	/**
	 * This method updates the orders of user for the given ordersId,
	 *  and return the updated orders. 
	 * @param orderId
	 * @param Orders
	 * @return Orders
	 */
	Orders updateOrders(Integer orderID);
	
	/**
	 * This method deletes the given order using order id;
	 * @param orderId
	 * @return orders;
	 */
	Orders deleteOrders(Integer orderId);
	
	/**
	 * This method return all the user orders
	 * @param orderId
	 * @return Orders
	 */
	Orders viewOrder(Integer orderId);
	
	/**
	 * This Method returns the list of Orders.
	 * @return List<Orders>
	 */
	List<Orders> viewAllOrders(Integer userId); 
}
