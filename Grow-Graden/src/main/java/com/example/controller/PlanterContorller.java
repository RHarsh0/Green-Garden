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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Cart;
import com.example.model.Planter;
import com.example.service.PlanterService;

@RestController
public class PlanterContorller {				// Planters controller

	private PlanterService planterService;
	
	public PlanterContorller(PlanterService planterService) {
		super();
		this.planterService = planterService;
	}


//	@PostMapping("/planters/{userId}/{planterId}/{quantity}")
//	public ResponseEntity<Cart> addPlanter(@PathVariable Integer userId, @PathVariable Integer planterId,@PathVariable Integer quantity) {
//		return new ResponseEntity<Cart>(PlanterService.addPlanter(userId, planterId,quantity), HttpStatus.CREATED);
//	}


	@DeleteMapping("/planters/{planterId}")					// Delete the planter by planter id
	public ResponseEntity<String> deletePlaterById(@PathVariable Integer planterId) {
		return new ResponseEntity<String>(planterService.deletePlanter(planterId), HttpStatus.OK);
	}

	@GetMapping("/planters/{planterId}")					// get the planter by planter id
	public ResponseEntity<Planter> viewPlanterById(@PathVariable Integer planterId) {
		return new ResponseEntity<Planter>(planterService.viewPlanterById(planterId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/planters")
	public ResponseEntity<List<Planter>> viewAllPlanter(			// get the list of all the planters available
			@RequestParam(defaultValue = "0", required = false) Integer page,
			@RequestParam(defaultValue = "10", required = false) Integer size,
			@RequestParam(required = false) Map<String,String> map) {
		return new ResponseEntity<List<Planter>>(planterService.viewAllPlanter(page,size,map), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/plantersBy/{shape}")				// show all the planters with their shapes
	public ResponseEntity<List<Planter>> ViewPlanterByPlanterShape(@PathVariable String shape){
		return new ResponseEntity<List<Planter>>(planterService.ViewPlanterByPlanterShape(shape),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/planters/{min}/{max}")				// show all the planters within the price range
	public ResponseEntity<List<Planter>> viewAllPlantersbetweenRange(@PathVariable Double min,@PathVariable Double max){
		return new ResponseEntity<List<Planter>>(planterService.viewAllPlantersbetweenRange(min, max),HttpStatus.ACCEPTED);
	}
	
}
