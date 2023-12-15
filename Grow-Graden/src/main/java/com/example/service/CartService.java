package com.example.service;

import com.example.model.Cart;
import com.example.request.CartRequest;

public interface CartService {
	
	/**
	 * This method adds the planter to the cart;
	 * @param CartRequest
	 * @return Cart
	 */
	public Cart addPlanterInCart(CartRequest cartRequest);
	
	/**
	 * This method adds the product to the cart;
	 * @param CartRequest
	 * @return Cart
	 */
	public Cart addproductIntoCart(CartRequest cartRequest);
	
	/**
	 * This method views the cart by using the userId;
	 * @param userId
	 * @return Cart
	 */
	public Cart viewCart(Integer userId);
	
	/**
	 * This method delets the product that are present inside the cart;
	 * @param productId
	 * @param userId
	 * @return String
	 */
	public String deleteProductFromUserCartUsingUserId(Integer productId , Integer userId);
	
}
