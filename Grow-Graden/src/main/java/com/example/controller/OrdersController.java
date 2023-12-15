package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Cart;
import com.example.model.Orders;
import com.example.service.OrderServiceInteface;

@RestController
@RequestMapping("/orders")
public class OrdersController {							// Orders can be controlled from the following endpoints
	
	private OrderService orderService;

	@Autowired
	public OrdersController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/{userId}")
	public ResponseEntity<Cart> addOrdersFromCart(@PathVariable Integer userId){			// Add the orders from cart
		return new ResponseEntity<Cart>(orderService.addOrdersFromCart(userId),HttpStatus.OK);
	}

	@PutMapping("/{orderID}")
	public ResponseEntity<Orders> updateExistingOrder(@PathVariable Integer orderID) {				// update the orders
		return new ResponseEntity<Orders>(orderService.updateOrders(orderID),HttpStatus.OK);
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<Orders> deleteOrder(@PathVariable Integer orderId){				// Delete the orders
		return new ResponseEntity<Orders>(orderService.deleteOrders(orderId),HttpStatus.OK);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Orders> viewOrder(@PathVariable Integer orderId){					// View  order by id
		return new ResponseEntity<Orders>(orderService.viewOrder(orderId),HttpStatus.OK);
	}

	@GetMapping("/allOrders/{userId}")
	public ResponseEntity<List<Orders>> viewAllOrders(@PathVariable Integer userId){			// view all the orders
		return new ResponseEntity<List<Orders>>(orderService.viewAllOrders(userId),HttpStatus.OK);
	}
}
