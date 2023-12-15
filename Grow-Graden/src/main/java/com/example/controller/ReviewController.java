package com.example.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Review;
import com.example.service.ReviewService;

@RestController
public class ReviewController {                    // the review controller

    private  ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {             // create a review
         Review createdReview =reviewService.createReview(review);
         
         return new ResponseEntity<Review>(createdReview, HttpStatus.CREATED);
         
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<Review>> getReviewById(@PathVariable Integer id) {        // view the review using id
        Optional<Review>review = reviewService.getReviewById(id);
        
        if (review.isPresent()) {
            return new ResponseEntity<List<Review>>((List<Review>) review.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }
    
    
    @GetMapping("/reviews/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Integer productId) {            // view the review by product id

    	List<Review> reviews = reviewService.getReviewsByProductId(productId);
        
    	
        return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Integer userId) {                //view the review by user id

    	List<Review> reviews = reviewService.getReviewsByUserId(userId);
   
    	return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
   
    	
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer id) {                                // delete the review by id
            
            return new ResponseEntity<String>(reviewService.deleteReview(id),HttpStatus.NO_CONTENT);
       
    }
}
