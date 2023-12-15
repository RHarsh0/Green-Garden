package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PlanterDto;
import com.example.model.Plant;
import com.example.model.Planter;
import com.example.model.Product;
import com.example.model.Seed;
import com.example.responce.ApiResponse;
import com.example.service.PlanterService;
import com.example.service.PlanterServiceImpl;
import com.example.service.ProductService;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

	private ProductService productService;
	private UserService userService;
	private PlanterService planterService;

	
	
	@Autowired
	public AdminProductController(ProductService productService, UserService userService,
			PlanterService planterService) {
		super();
		this.productService = productService;
		this.userService = userService;
		this.planterService = planterService;
	}

	@PostMapping("/plant")
	public ResponseEntity<Product> createPlant(@RequestBody Plant plant) {                                 // Add new plant
		return new ResponseEntity<Product>(productService.createProduct(plant), HttpStatus.CREATED);
	}

	@PostMapping("/seed")
	public ResponseEntity<Product> createSeed(@RequestBody Seed seed) {				      // Add new seed
		return new ResponseEntity<Product>(productService.createProduct(seed), HttpStatus.CREATED);
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<ApiResponse> deleteProductByProductID(@PathVariable Integer productId) { 		     // Delete a product
		ApiResponse apiResponse = new ApiResponse(productService.deleteProductById(productId), true);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/products/all")
	public ResponseEntity<List<Product>> findAllProducts() {                                             //get the list of all products available
		List<Product> products = productService.allProdcuts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/plants")
	public ResponseEntity<List<Product>> getAllPlant() {                    			    // get the list of all plants
		List<Product> products = productService.getAllPlants();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/seeds")
	public ResponseEntity<List<Product>> getAllSeed() {                                                // get the list of all the seeds
		List<Product> products = productService.getAllSeeds();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/planters")
	public ResponseEntity<List<Planter>> viewAllPlanter(						  // get the list of all planters
			@RequestParam(defaultValue = "0", required = false) Integer page,
			@RequestParam(defaultValue = "10", required = false) Integer size,
			@RequestParam(required = false) Map<String,String> map) {
		return new ResponseEntity<List<Planter>>(planterService.viewAllPlanter(page,size,map), HttpStatus.ACCEPTED);
	}

	
	@PostMapping("/planter")
	public ResponseEntity<Planter> addPlanter(@Valid @RequestBody Planter planter) {		   // add a new planter
		return new ResponseEntity<Planter>(planterService.addPlanter(planter), HttpStatus.CREATED);
	}

	@PutMapping("/planters")
	public ResponseEntity<Planter> updatePlanter(@RequestBody PlanterDto planter) {			  // update the details of a planter
		return new ResponseEntity<Planter>(planterService.updatePlanter(planter), HttpStatus.CREATED);
	}
	

}
