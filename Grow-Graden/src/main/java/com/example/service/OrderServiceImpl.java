package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.CartException;
import com.example.exception.OrderException;
import com.example.exception.UserException;
import com.example.model.Cart;
import com.example.model.Orders;
import com.example.model.Planter;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.CartRepository;
import com.example.repository.OrdersRepository;
import com.example.repository.PlanterRepository;
import com.example.repository.UserRepository;

@Service
public class OrderServiceImplClass implements OrderServiceInteface {

	private UserRepository userRepository;

	private PlanterRepository planterRepositoryInterface;

	private OrdersRepository ordersRepository;

	private CartRepository cartRepository;
	


	@Autowired
	public OrderServiceImplClass(PlanterRepository planterRepositoryInterface,
			OrdersRepository ordersRepository, UserRepository userRepository, CartRepository cartRepository) {
		this.planterRepositoryInterface = planterRepositoryInterface;
		this.ordersRepository = ordersRepository;
		this.userRepository = userRepository;
		this.cartRepository = cartRepository;
	}

	@Override
	public Cart addOrdersFromCart(Integer userId) {
		System.out.println(userId);
		
		Cart cart = cartRepository.findByUserId(userId);
//		System.out.println(cart);
		
//		if(cart == null) {
//			throw new CartException("Cart is empty, please add products to order.");
//		}
//		Orders order = new Orders();
//		order.setOrderDate(LocalDate.now());
//		order.setPlanters(new ArrayList<>());
//		order.setProducts(new ArrayList<>());
//		order.setTotalCost(cart.getTotalPrice());
//		
//		List<Product> products = user.getProductList();
//		List<Planter> planter = user.getPlanter();
//		
//		order.setPlanters(planter);
//		order.setProducts(products);
//		ordersRepository.save(order);
//		return order;
		return cart;
	}

	@Override
	public Orders updateOrders(Integer orderID) {
		Orders order = ordersRepository.findById(orderID).orElseThrow(() -> new OrderException("No order found for the given order id."));
		return order;
	}

	@Override
	public Orders deleteOrders(Integer orderId) {
		Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new OrderException("No order found for the given order id."));
		ordersRepository.deleteById(orderId);		
		return order;
	}

	@Override
	public Orders viewOrder(Integer orderId) {
		return ordersRepository.findById(orderId).orElseThrow(() -> new OrderException("No order found for the given order id."));
	}

	@Override
	public List<Orders> viewAllOrders(Integer userId) {
		User user = userRepository.findById(userId).get();
		return ordersRepository.findByUser(user);
	}

} 
