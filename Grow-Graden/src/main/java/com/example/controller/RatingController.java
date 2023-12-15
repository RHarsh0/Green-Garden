package com.example.controller;

import com.example.model.Rating;
import com.example.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RatingController {					//  To perfrom CRUD operation on rating

	private RatingService ratingService;

	@Autowired
	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@PostMapping("/ratings")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {			// Provide rating
		Rating createdRating = ratingService.createRating(rating);

		return new ResponseEntity<Rating>(createdRating, HttpStatus.CREATED);

	}

	@GetMapping("/ratings/{id}")
	public ResponseEntity<List<Rating>> getRatingById(@PathVariable Integer id) {			// View rating by id
		Optional<Rating> rating = ratingService.getRatingById(id);

		if (rating.isPresent()) {
			return new ResponseEntity<List<Rating>>((List<Rating>) rating.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/ratings/product/{productId}")
	public ResponseEntity<List<Rating>> getRatingsByProductId(@PathVariable Integer productId) {		// get the rating of a product by using productid

		List<Rating> ratings = ratingService.getRatingsByProductId(productId);

		return new ResponseEntity<List<Rating>>(ratings, HttpStatus.OK);

	}

	@GetMapping("/ratings/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable Integer userId) {			//get rating of user by user id

		List<Rating> ratings = ratingService.getRatingsByUserId(userId);

		return new ResponseEntity<List<Rating>>(ratings, HttpStatus.OK);

	}
	
	 @DeleteMapping("/ratings/{id}")
	    public ResponseEntity<String> deleteRating(@PathVariable Integer id) {				// delete the rating
	            
	            return new ResponseEntity<String>(ratingService.deleteRating(id),HttpStatus.NO_CONTENT);
	       
	    }

}
