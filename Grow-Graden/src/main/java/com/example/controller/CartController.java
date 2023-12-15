package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Cart;
import com.example.request.CartRequest;
import com.example.service.CartService;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	private CartService cartService;
	
	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@PostMapping("/planter")
	private ResponseEntity<Cart> addPlanterToCart(@RequestBody CartRequest cartRequest){			// Add a planter to the cart
		Cart cart =  cartService.addPlanterInCart(cartRequest);
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}

	@PostMapping("/products")
	private ResponseEntity<Cart> addProductToCart(@RequestBody CartRequest cartRequest){			// Add a new product to the cart
		Cart cart = cartService.addproductIntoCart(cartRequest);
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Cart> viewUsersCartByUserId(@PathVariable Integer userId){			// View the cart of the user
		return new ResponseEntity<Cart>(cartService.viewCart(userId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}/{userId}")
	public ResponseEntity<String> deleteProductFromUserCartUsingUserId(@PathVariable Integer productId,@PathVariable Integer userId){		// Delete the product from the cart
		System.out.println(productId + " " + userId);
		 return new ResponseEntity<String>(cartService.deleteProductFromUserCartUsingUserId(productId, userId),HttpStatus.OK);
	
	}
	
	
}
