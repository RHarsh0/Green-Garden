package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.CartException;
import com.example.exception.PlanterNotFoundException;
import com.example.exception.ProductException;
import com.example.exception.UserException;
import com.example.model.Cart;
import com.example.model.Planter;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.CartRepository;
import com.example.repository.PlanterRepository;

import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.request.CartRequest;

@Service
public class CartServcieImpl implements CartService {

	private CartRepository cartRepository;
	private ProductRepository productRepository;
	private PlanterRepository planterRepository;
	private UserRepository userRepository;

	public CartServcieImpl(CartRepository cartRepository, ProductRepository productRepository,
			PlanterRepository planterRepository, UserRepository userRepository) {
		super();
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
		this.planterRepository = planterRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Cart addPlanterInCart(CartRequest cartRequest) {
		// TODO Auto-generated method stub
		Optional<User> userOpt = userRepository.findById(cartRequest.getUserId());
		User user = userOpt.orElseThrow(() -> new UserException("Invalid User Id " + cartRequest.getUserId()));

		Optional<Planter> planterOpt = planterRepository.findById(cartRequest.getProductId());

		Planter planter = planterOpt.orElseThrow(() -> new PlanterNotFoundException("Planter Not Found"));

		Double totalPrice = planter.getPlanterCost() * cartRequest.getQuantity();

		Cart cart = cartRepository.findByUserId(cartRequest.getUserId());

		if (cart == null) {
			cart = new Cart();
			cart.setQuantity(0);
			cart.setTotalPrice(0.0);
		}
			
			
		
		cart.getPlanterIds().add(planter.getPlanterId());
		Integer totalQuntity =  cart.getQuantity()+cartRequest.getQuantity();
		cart.setQuantity(totalQuntity);
		cart.setTotalPrice(cart.getTotalPrice()+totalPrice);
		cart.setUser(user);

		cartRepository.save(cart);

		return cart;
	}

	@Override
	public Cart addproductIntoCart(CartRequest cartRequest) {
		Optional<User> userOpt = userRepository.findById(cartRequest.getUserId());
		User user = userOpt.orElseThrow(() -> new UserException("Invalid User Id " + cartRequest.getUserId()));
		Optional<Product> producopt = productRepository.findById(cartRequest.getProductId());
		Product product = producopt.orElseThrow(() -> new ProductException("Product Not Found"));
		Double totalPrice = product.getCost() * cartRequest.getQuantity();
		Cart cart = cartRepository.findByUserId(cartRequest.getUserId());
		if (cart == null) {
			cart = new Cart();
			cart.setQuantity(0);
			cart.setTotalPrice(0.0);
		}
			
		cart.getProductIds().add(product.getProductId());
		cart.setQuantity(cart.getQuantity()+cartRequest.getQuantity());
		cart.setTotalPrice(cart.getTotalPrice()+totalPrice);
		cart.setUser(user);

		cartRepository.save(cart);
		return cart;
	}

	@Override
	public Cart viewCart(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User not found for the given userId " + userId));
		if (user.getCart() == null) {
			throw new CartException("Cart of the User is empty please add some product.");
		}
		return user.getCart();
	}

	@Override
	public String deleteProductFromUserCartUsingUserId(Integer productId , Integer userId) {
		// TODO Auto-generated method stub
		Cart userCart  = cartRepository.findByUserId(userId);
		
		 Integer quantity = 0;
		 if(userCart.getPlanterIds().contains(productId)) {
			
			 Optional<Planter> optional = planterRepository.findById(productId);
			 if(optional.isPresent()) {
				 Planter planter = optional.get();
				 userCart.setTotalPrice(userCart.getTotalPrice()-planter.getPlanterCost()); ;
				 quantity++;
				 userCart.getPlanterIds().remove(productId);
			 }
			
		 }
		
		 if(userCart.getProductIds().contains(productId)) {
			 Optional<Product> optional = productRepository.findById(productId);
			 if(optional.isPresent()) {
				 Product product = optional.get();
				 userCart.setTotalPrice(userCart.getTotalPrice()-product.getCost());
				 quantity++;
				 userCart.getProductIds().remove(productId);
			 }
		
			
		 }
		 
		
		 userCart.setQuantity(userCart.getQuantity()-quantity); 
		 
		 
		 cartRepository.save(userCart);
		
		return "Product Remove Successfully";
	}
}
